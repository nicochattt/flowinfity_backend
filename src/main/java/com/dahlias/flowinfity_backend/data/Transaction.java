package com.dahlias.flowinfity_backend.data;
import jakarta.persistence.*;
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransaction")
    private long id;
}
