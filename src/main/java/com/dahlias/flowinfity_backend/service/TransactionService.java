package com.dahlias.flowinfity_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.data.Transaction;
import com.dahlias.flowinfity_backend.repository.AssociationRepository;
import com.dahlias.flowinfity_backend.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AssociationRepository associationRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        Association association = associationRepository.findById(transaction.getAssociation().getId())
                .orElseThrow(() -> new RuntimeException("Association not found with id: " + transaction.getAssociation().getId()));

        if (transaction.getOuOrIn()) {
            // If ouOrIn is true, subtract the transaction value from the association's money
            association.setMoney(association.getMoney().subtract(transaction.getValeur()));
        } else {
            // If ouOrIn is false, add the transaction value to the association's money
            association.setMoney(association.getMoney().add(transaction.getValeur()));
        }

        associationRepository.save(association);
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        transaction.setTransactionName(transactionDetails.getTransactionName());
        transaction.setValeur(transactionDetails.getValeur());
        transaction.setDate(transactionDetails.getDate());
        transaction.setOuOrIn(transactionDetails.getOuOrIn());

        Association association = associationRepository.findById(transactionDetails.getAssociation().getId())
                .orElseThrow(() -> new RuntimeException("Association not found with id: " + transactionDetails.getAssociation().getId()));
        transaction.setAssociation(association);

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        transactionRepository.delete(transaction);
    }
}
