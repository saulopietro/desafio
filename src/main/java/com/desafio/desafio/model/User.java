package com.desafio.desafio.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private Double balance;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String cnpj;

    private TypeEnum type;

    @OneToMany(mappedBy = "client")
    private List<Transaction> transaction;

    public User(Long id, TypeEnum type, String cnpj, String cpf, Double balance, String password, String email, String name, String cadastro) {
        this.id = id;
        this.balance = balance;
        this.password = password;
        this.email = email;
        this.name = name;
        if(cadastro.length() == 11) {
            this.cpf = cadastro;
            this.type = TypeEnum.CUSTOMER;
        } else if (cadastro.length() == 14) {
            this.cnpj = cadastro;
            this.type = TypeEnum.SELLER;
        }
    }

    public User() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
