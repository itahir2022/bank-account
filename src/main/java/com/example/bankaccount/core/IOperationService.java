package com.example.bankaccount.core;

import com.example.bankaccount.domain.dto.OperationDto;
import com.example.bankaccount.domain.dto.TransactionDto;
import com.example.bankaccount.domain.exception.BankTechnicalException;

import java.util.List;

public interface IOperationService {

    OperationDto deposit(TransactionDto dto) throws BankTechnicalException;

    OperationDto withdrawal(TransactionDto dto) throws BankTechnicalException;

    List<OperationDto> statement(Long accountNumber);
}
