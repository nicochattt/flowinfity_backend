package com.dahlias.flowinfity_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dahlias.flowinfity_backend.data.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.association.id = :associationId")
    List<Transaction> findAllTransactionByAssociationId(@Param("associationId") Long associationId);
}
