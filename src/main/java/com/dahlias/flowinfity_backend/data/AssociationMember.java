package com.dahlias.flowinfity_backend.data;

import jakarta.persistence.*;

@Entity
@Table(name = "associationmember")
public class AssociationMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idassociationmember")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_iduser", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "association_idassociation", nullable = false)
    private Association association;

    @Enumerated(EnumType.STRING)
    @Column(name = "associationmemberstatut")
    private AssociationMemberStatut statut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public AssociationMemberStatut getStatut() {
        return statut;
    }

    public void setStatut(AssociationMemberStatut statut) {
        this.statut = statut;
    }

}
