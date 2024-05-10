package com.dahlias.flowinfity_backend.data;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "demanderebourcement")
public class DemandeRemboursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddemanderebourcement")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "imagepath")
    private String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private DemandeRemboursementStatut statut;

    @ManyToOne
    @JoinColumn(name = "user_iduser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "association_idassociation", nullable = false)
    private Association association;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public DemandeRemboursementStatut getStatut() {
        return statut;
    }

    public void setStatut(DemandeRemboursementStatut statut) {
        this.statut = statut;
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
}
