package com.dahlias.flowinfity_backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dahlias.flowinfity_backend.data.DemandeRemboursement;
import com.dahlias.flowinfity_backend.data.DemandeRemboursementStatut;
import com.dahlias.flowinfity_backend.service.DemandeRemboursementService;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeRemboursementController {

    @Autowired
    private DemandeRemboursementService demandeRemboursementService;

    @GetMapping
    public List<DemandeRemboursement> getAllDemandes() {
        return demandeRemboursementService.getAllDemandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeRemboursement> getDemandeById(@PathVariable Long id) {
        return demandeRemboursementService.getDemandeById(id)
                .map(demande -> ResponseEntity.ok().body(demande))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createDemande(@RequestBody DemandeRemboursement demande) {
        try {
            DemandeRemboursement savedDemande = demandeRemboursementService.createDemande(demande);
            return ResponseEntity.ok(savedDemande);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeRemboursement> updateDemande(@PathVariable Long id,
            @RequestBody DemandeRemboursement demandeDetails) {
        return ResponseEntity.ok(demandeRemboursementService.updateDemande(id, demandeDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        demandeRemboursementService.deleteDemande(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<DemandeRemboursement> updateStatut(@PathVariable Long id,
            @RequestBody DemandeRemboursementStatut statut) {
        return ResponseEntity.ok(demandeRemboursementService.updateStatut(id, statut));
    }
}
