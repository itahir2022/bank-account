package com.example.bankaccount.persist.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@SequenceGenerator(name = "account_seq_gen", sequenceName = "account_seq", allocationSize = 1)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
    @Column(name = "id_account")
    private Long id;

    @Column(unique = true, nullable = false)
    private Long number;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
}
