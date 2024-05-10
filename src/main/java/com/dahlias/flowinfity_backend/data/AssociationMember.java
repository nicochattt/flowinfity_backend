package com.dahlias.flowinfity_backend.data;

import jakarta.persistence.*;

@Entity
@Table(name = "associationmember")
@IdClass(AssociationMemberId.class)
public class AssociationMember {
    @Id
    @Column(name = "user_iduser")
    private Long userId;

    @Id
    @Column(name = "association_idassociation")
    private Long associationId;

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
    }
}
