package com.dahlias.flowinfity_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dahlias.flowinfity_backend.data.AssociationMember;
import com.dahlias.flowinfity_backend.data.AssociationMemberDTO;
import com.dahlias.flowinfity_backend.data.AssociationMemberStatut;
import com.dahlias.flowinfity_backend.data.User;
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

    @GetMapping("/{Id}")
    public ResponseEntity<AssociationMember> getAssociationMemberById(@PathVariable Long id) {
        return associationMemberService.getAssociationMemberById(id)
                .map(associationMember -> ResponseEntity.ok().body(associationMember))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AssociationMember createAssociationMember(@RequestBody AssociationMember associationMember) {
        return associationMemberService.createAssociationMember(associationMember);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<AssociationMember> updateAssociationMember(@PathVariable Long id,
            @RequestBody AssociationMember associationMemberDetails) {
        return ResponseEntity.ok(associationMemberService.updateAssociationMember(id, associationMemberDetails));
    }

    @DeleteMapping("/{userId}/{associationId}")
    public ResponseEntity<Void> deleteAssociationMember(@PathVariable Long id) {
        associationMemberService.deleteAssociationMember(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{associationId}/members")
    public ResponseEntity<List<User>> getAssociationMembers(@PathVariable Long associationId) {
        List<User> members = associationMemberService.getMembersByAssociationId(associationId);
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{associationId}/{statut}/members")
    public ResponseEntity<List<User>> getAssociationMembersByStatut(@PathVariable Long associationId,
            AssociationMemberStatut statut) {
        List<User> members = associationMemberService.getMembersByAssociationIdAndStatut(associationId, statut);
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<AssociationMember> updateStatut(@PathVariable Long id,
            @RequestBody AssociationMemberStatut statut) {
        return ResponseEntity.ok(associationMemberService.updateStatut(id, statut));
    }

    @GetMapping("/{associationId}/statut/members")
    public ResponseEntity<List<AssociationMemberDTO>> getMembersWithStatusByAssociationId(
            @PathVariable Long associationId) {
        List<AssociationMemberDTO> members = associationMemberService
                .getMembersWithStatusByAssociationId(associationId);
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }
}
