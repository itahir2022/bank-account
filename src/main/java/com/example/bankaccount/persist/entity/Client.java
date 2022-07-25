package com.example.bankaccount.persist.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@SequenceGenerator(name = "client_seq_gen", sequenceName = "client_seq", allocationSize = 1)
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq_gen")
    @Column(name = "id_client")
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private Long number;
}
