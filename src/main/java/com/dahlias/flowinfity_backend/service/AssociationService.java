package com.dahlias.flowinfity_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.data.AssociationStatut;
import com.dahlias.flowinfity_backend.repository.AssociationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {
    @Autowired
    private AssociationRepository associationRepository;

    public List<Association> getAllAssociations() {
        return associationRepository.findAll();
    }

    public Optional<Association> getAssociationById(Long id) {
        return associationRepository.findById(id);
    }

    public Association createAssociation(Association association) {
        return associationRepository.save(association);
    }

    public Association updateAssociation(Long id, Association associationDetails) {
        Association association = associationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Association not found with id: " + id));
        association.setAssociationName(associationDetails.getAssociationName());
        association.setBudget(associationDetails.getBudget());
        association.setRib(associationDetails.getRib());
        association.setAssociationStatut(associationDetails.getAssociationStatut());
        return associationRepository.save(association);
    }

    public void deleteAssociation(Long id) {
        Association association = associationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Association not found with id: " + id));
        associationRepository.delete(association);
    }

    public List<Association> getAssociationByStatut(AssociationStatut statut) {
        return associationRepository.findAllByStatut(statut);
    }

    @Cacheable(value = "association", key = "#name")
    public List<Association> searchAssociationByName(String name) {
        return associationRepository.findByAssociationName(name);
    }

}
