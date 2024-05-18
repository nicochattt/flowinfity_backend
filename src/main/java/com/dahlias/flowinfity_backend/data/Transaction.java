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

    @Column(name = "outorin")
    private Boolean outOrIn;

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

    public Boolean getOutOrIn() {
        return outOrIn;
    }

    public void setOutOrIn(Boolean outOrIn) {
        this.outOrIn = outOrIn;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }
}