package com.dahlias.flowinfity_backend.data;

public class AssociationMemberDTO {
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private AssociationMemberStatut statut;

    public AssociationMemberDTO(User user, AssociationMemberStatut statut) {
        this.userId = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.statut = statut;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AssociationMemberStatut getStatut() {
        return this.statut;
    }

    public void setStatut(AssociationMemberStatut statut) {
        this.statut = statut;
    }

}
