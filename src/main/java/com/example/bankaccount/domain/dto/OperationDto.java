package com.example.bankaccount.domain.dto;

import lombok.Data;

import java.util.Date;
@Data
public class OperationDto {

    private String type;

    private Date date;

    private Double amount;

    private Double balance;

}
