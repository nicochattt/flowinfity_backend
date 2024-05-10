package com.dahlias.flowinfity_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dahlias.flowinfity_backend.data.AssociationMember;
import com.dahlias.flowinfity_backend.data.AssociationMemberId;
import com.dahlias.flowinfity_backend.repository.AssociationMemberRepository;

@Service
public class AssociationMemberService {
        @Autowired
    private AssociationMemberRepository associationMemberRepository;

    public List<AssociationMember> getAllAssociationMembers() {
        return associationMemberRepository.findAll();
    }

    public Optional<AssociationMember> getAssociationMemberById(Long userId, Long associationId) {
        return associationMemberRepository.findById(new AssociationMemberId(userId, associationId));
    }

    public AssociationMember createAssociationMember(AssociationMember associationMember) {
        return associationMemberRepository.save(associationMember);
    }

    public AssociationMember updateAssociationMember(Long userId, Long associationId, AssociationMember associationMemberDetails) {
        AssociationMemberId id = new AssociationMemberId(userId, associationId);
        AssociationMember associationMember = associationMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AssociationMember not found with id: " + id));
        associationMember.setUserId(associationMemberDetails.getUserId());
        associationMember.setAssociationId(associationMemberDetails.getAssociationId());
        return associationMemberRepository.save(associationMember);
    }

    public void deleteAssociationMember(Long userId, Long associationId) {
        AssociationMemberId id = new AssociationMemberId(userId, associationId);
        AssociationMember associationMember = associationMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AssociationMember not found with id: " + id));
        associationMemberRepository.delete(associationMember);
    }
}
