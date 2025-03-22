package com.desafio.desafio.model;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User client;

    private Double amount;


    public Transaction(Long id, User client, Double amount) {
        this.id = id;
        this.client = client;
        this.amount = amount;
    }


    public Transaction() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User user) {
        this.client = user;
    }
}
