package com.dahlias.flowinfity_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dahlias.flowinfity_backend.data.DemandeRemboursement;
import com.dahlias.flowinfity_backend.repository.DemandeRemboursementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeRemboursementService {

    @Autowired
    private DemandeRemboursementRepository demandeRemboursementRepository;

    public List<DemandeRemboursement> getAllDemandes() {
        return demandeRemboursementRepository.findAll();
    }

    public Optional<DemandeRemboursement> getDemandeById(Long id) {
        return demandeRemboursementRepository.findById(id);
    }

    public DemandeRemboursement createDemande(DemandeRemboursement demande) {
        return demandeRemboursementRepository.save(demande);
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
}
