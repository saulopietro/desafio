package com.desafio.desafio.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    private Double amount;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum status;


    public Transaction(Long id, User seller, Double amount, LocalDate date, TransactionStatusEnum status) {
        this.id = id;
        this.seller = seller;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    public Transaction() {
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public TransactionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransactionStatusEnum status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public User getSeller() {
        return seller;
    }

    public void setSeller(User user) {
        this.seller = user;
    }
}
