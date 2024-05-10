package com.dahlias.flowinfity_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dahlias.flowinfity_backend.data.AssociationMember;
import com.dahlias.flowinfity_backend.service.AssociationMemberService;


@RestController
@RequestMapping("/api/associationmembers")
public class AssociationMemberController {
    
    @Autowired
    private AssociationMemberService associationMemberService;

    @GetMapping
    public List<AssociationMember> getAllAssociationMembers() {
        return associationMemberService.getAllAssociationMembers();
    }

    @GetMapping("/{userId}/{associationId}")
    public ResponseEntity<AssociationMember> getAssociationMemberById(@PathVariable Long userId, @PathVariable Long associationId) {
        return associationMemberService.getAssociationMemberById(userId, associationId)
                .map(associationMember -> ResponseEntity.ok().body(associationMember))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AssociationMember createAssociationMember(@RequestBody AssociationMember associationMember) {
        return associationMemberService.createAssociationMember(associationMember);
    }

    @PutMapping("/{userId}/{associationId}")
    public ResponseEntity<AssociationMember> updateAssociationMember(@PathVariable Long userId, @PathVariable Long associationId, @RequestBody AssociationMember associationMemberDetails) {
        return ResponseEntity.ok(associationMemberService.updateAssociationMember(userId, associationId, associationMemberDetails));
    }

    @DeleteMapping("/{userId}/{associationId}")
    public ResponseEntity<Void> deleteAssociationMember(@PathVariable Long userId, @PathVariable Long associationId) {
        associationMemberService.deleteAssociationMember(userId, associationId);
        return ResponseEntity.ok().build();
    }
}
