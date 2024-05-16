package com.dahlias.flowinfity_backend.data;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "association")
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idassociation")
    private long id;

    @Column(name = "associationname")
    private String associationName;

    @Column(name = "budget")
    private BigDecimal budget;

    @Column(name = "rib")
    private String rib;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }
}
