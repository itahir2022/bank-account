package com.example.bankaccount.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDto {

    private Double amount;

    private Long accountNumber;
}
