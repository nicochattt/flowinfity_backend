package com.dahlias.flowinfity_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dahlias.flowinfity_backend.data.DemandeRemboursement;
import com.dahlias.flowinfity_backend.data.DemandeRemboursementStatut;
import com.dahlias.flowinfity_backend.data.Transaction;
import com.dahlias.flowinfity_backend.data.User;
import com.dahlias.flowinfity_backend.repository.DemandeRemboursementRepository;
import com.dahlias.flowinfity_backend.repository.TransactionRepository;
import com.dahlias.flowinfity_backend.util.AssociationUtil;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeRemboursementService {

    @Autowired
    private DemandeRemboursementRepository demandeRemboursementRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmailService emailService;

    public List<DemandeRemboursement> getAllDemandes() {
        return demandeRemboursementRepository.findAll();
    }

    public Optional<DemandeRemboursement> getDemandeById(Long id) {
        return demandeRemboursementRepository.findById(id);
    }

    public DemandeRemboursement createDemande(DemandeRemboursement demande) {
        
        DemandeRemboursement savedDemande = demandeRemboursementRepository.save(demande);
        sendEmailCreation(savedDemande);
        return savedDemande;
        
    }

    public DemandeRemboursement updateDemande(Long id, DemandeRemboursement demandeDetails) {
        DemandeRemboursement demande = demandeRemboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found with id: " + id));
        demande.setDescription(demandeDetails.getDescription());
        demande.setMontant(demandeDetails.getMontant());
        demande.setImagePath(demandeDetails.getImagePath());
        demande.setStatut(demandeDetails.getStatut());
        demande.setUser(demandeDetails.getUser());
        demande.setAssociation(demandeDetails.getAssociation());
        return demandeRemboursementRepository.save(demande);
    }

    public void deleteDemande(Long id) {
        DemandeRemboursement demande = demandeRemboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found with id: " + id));
        demandeRemboursementRepository.delete(demande);
    }

    public DemandeRemboursement updateStatut(Long id, DemandeRemboursementStatut statut) {
        DemandeRemboursement demande = demandeRemboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found with id: " + id));

        DemandeRemboursementStatut oldStatut = demande.getStatut();
        demande.setStatut(statut);
        demandeRemboursementRepository.save(demande);

        if (oldStatut == DemandeRemboursementStatut.PENDING && statut == DemandeRemboursementStatut.ACCEPTED) {
            generateTransactionForAcceptedDemande(demande);
        }
        sendEmailNotification(demande);
        return demande;
    }

    private void generateTransactionForAcceptedDemande(DemandeRemboursement demande) {
        User user = demande.getUser();
        String transactionName = "Remboursement à " + user.getFirstname() + " " + user.getLastname();

        Transaction transaction = new Transaction();
        transaction.setTransactionName(transactionName);
        transaction.setValeur(demande.getMontant());
        transaction.setDate(java.time.LocalDateTime.now());
        transaction.setOutOrIn(true);
        transaction.setAssociation(demande.getAssociation());

        transactionRepository.save(transaction);
        AssociationUtil.updateAssociationMoney(demande.getAssociation(), demande.getMontant());
    }
    private void sendEmailNotification(DemandeRemboursement demande) {
        User user = demande.getUser();
        String to = user.getEmail();
        String subject = "Mise à jour de votre demande de remboursement";
        String text = String.format("Bonjour %s %s,\n\nVotre demande de remboursement a été mise à jour.\n\nDétails de la demande:\nDescription: %s\nMontant: %s\nStatut: %s\n\nMerci.",
                user.getFirstname(), user.getLastname(), demande.getDescription(), demande.getMontant(), demande.getStatut());

        emailService.sendEmail(to, subject, text);
    }
    private void sendEmailCreation(DemandeRemboursement demande) {
        User user = demande.getUser();
        String to = user.getEmail();
        String subject = "votre demande de remboursement à bien été crée";
        String text = String.format("Bonjour %s %s,\n\nVotre demande de remboursement a été mise à jour.\n\nDétails de la demande:\nDescription: %s\nMontant: %s\nStatut: %s\n\nMerci.",
                user.getFirstname(), user.getLastname(), demande.getDescription(), demande.getMontant(), demande.getStatut());

        emailService.sendEmail(to, subject, text);
    }
}
