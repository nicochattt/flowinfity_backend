package com.dahlias.flowinfity_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dahlias.flowinfity_backend.data.AssociationMember;
import com.dahlias.flowinfity_backend.data.AssociationMemberDTO;
import com.dahlias.flowinfity_backend.data.AssociationMemberStatut;
import com.dahlias.flowinfity_backend.data.User;
import com.dahlias.flowinfity_backend.repository.AssociationMemberRepository;

@Service
public class AssociationMemberService {
    @Autowired
    private AssociationMemberRepository associationMemberRepository;
    @Autowired
    private EmailService emailService;

    public List<AssociationMember> getAllAssociationMembers() {
        return associationMemberRepository.findAll();
    }

    public Optional<AssociationMember> getAssociationMemberById(Long id) {
        return associationMemberRepository.findById(id);
    }

    public AssociationMember createAssociationMember(AssociationMember associationMember) {
        return associationMemberRepository.save(associationMember);
    }

    public AssociationMember updateAssociationMember(Long id, AssociationMember associationMemberDetails) {
        AssociationMember associationMember = associationMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AssociationMember not found with id: " + id));
        associationMember.setUser(associationMemberDetails.getUser());
        associationMember.setAssociation(associationMemberDetails.getAssociation());
        associationMember.setStatut(associationMemberDetails.getStatut());
        return associationMemberRepository.save(associationMember);
    }

    public void deleteAssociationMember(Long id) {
        AssociationMember associationMember = associationMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AssociationMember not found with id: " + id));
        associationMemberRepository.delete(associationMember);
    }

    public List<User> getMembersByAssociationId(Long associationId) {
        return associationMemberRepository.findAllMembersByAssociationId(associationId);
    }

    public List<User> getMembersByAssociationIdAndStatut(Long associationId, AssociationMemberStatut statut) {
        return associationMemberRepository.findAllMembersByAssociationIdAndStatut(associationId, statut);
    }

    public AssociationMember updateStatut(Long id, AssociationMemberStatut statut) {
        AssociationMember associationMember = associationMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("member not found with id: " + id));

        associationMember.setStatut(statut);
        associationMemberRepository.save(associationMember);

        sendEmailNotification(associationMember);
        return associationMember;
    }

    private void sendEmailNotification(AssociationMember associationMember) {
        User user = associationMember.getUser();
        String to = user.getEmail();
        String subject = "Mise à jour de votre statut dans l'association "
                + associationMember.getAssociation().getAssociationName();
        String text = String.format(
                "Bonjour %s %s,\n\nVotre statut dans l'association "
                        + associationMember.getAssociation().getAssociationName()
                        + " a été mise à jour.\n\nDétails de la modifiaction:\nAssociation: %s\nStatut: %s\n\nMerci.",
                user.getFirstname(), user.getLastname(), associationMember.getAssociation().getAssociationName(),
                associationMember.getStatut());

        emailService.sendEmail(to, subject, text);
    }

    public List<AssociationMemberDTO> getMembersWithStatusByAssociationId(Long associationId) {
        return associationMemberRepository.findMembersWithStatusByAssociationId(associationId);
    }
}
