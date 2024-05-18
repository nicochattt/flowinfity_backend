package com.dahlias.flowinfity_backend.web;

import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.data.AssociationStatut;
import com.dahlias.flowinfity_backend.service.AssociationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/associations")
public class AssociationController {
    @Autowired
    private AssociationService associationService;

    @GetMapping
    public List<Association> getAllAssociations() {
        return associationService.getAllAssociations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Association> getAssociationById(@PathVariable Long id) {
        return associationService.getAssociationById(id)
                .map(association -> ResponseEntity.ok().body(association))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Association createAssociation(@RequestBody Association association) {
        return associationService.createAssociation(association);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Association> updateAssociation(@PathVariable Long id,
            @RequestBody Association associationDetails) {
        return ResponseEntity.ok(associationService.updateAssociation(id, associationDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociation(@PathVariable Long id) {
        associationService.deleteAssociation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statut/{statut}")
    public List<Association> getaAssociationByStatut(@PathVariable AssociationStatut statut) {
        return associationService.getAssociationByStatut(statut);
    }

    @GetMapping("/search")
    public List<Association> searchAssociations(@RequestParam String name) {
        return associationService.searchAssociationByName(name);
    }

}
