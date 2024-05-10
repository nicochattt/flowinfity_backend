package com.dahlias.flowinfity_backend.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransaction")
    private Long id;

    @Column(name = "transactionname")
    private String transactionName;

    @Column(name = "valeur")
    private BigDecimal valeur;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "ouorin")
    private Boolean ouOrIn;

    @Column(name = "association_idassociation")
    private Long associationId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getOuOrIn() {
        return ouOrIn;
    }

    public void setOuOrIn(Boolean ouOrIn) {
        this.ouOrIn = ouOrIn;
    }

    public Long getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
    }
}