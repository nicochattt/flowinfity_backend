package com.dahlias.flowinfity_backend.data;

import java.io.Serializable;
import java.util.Objects;
public class AssociationMemberId implements Serializable{
       
    private Long userId;
    private Long associationId;

    public AssociationMemberId(Long userId, Long associationId) {
        //TODO Auto-generated constructor stub
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssociationMemberId that = (AssociationMemberId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(associationId, that.associationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, associationId);
    }
}
