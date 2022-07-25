package com.example.bankaccount.persist.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "operation_seq_gen", sequenceName = "operation_seq", allocationSize = 1)
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_seq_gen")
    @Column(name = "id_operation")
    private Long id;

    private String type;

    private Date date;

    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    private Account account;
}
