package com.example.bankaccount.core.impl;

import com.example.bankaccount.core.IOperationService;
import com.example.bankaccount.domain.dto.OperationDto;
import com.example.bankaccount.domain.dto.TransactionDto;
import com.example.bankaccount.domain.exception.BankTechnicalException;
import com.example.bankaccount.persist.entity.Account;
import com.example.bankaccount.persist.entity.Operation;
import com.example.bankaccount.persist.repository.AccountRepository;
import com.example.bankaccount.persist.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OperationServiceImpl implements IOperationService {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public OperationDto deposit(TransactionDto dto) throws BankTechnicalException {
        // Log d'entrée
        log.info("Entée, TransactionDto={}", dto);

        Operation operation = instanciateOperation("DEPOSIT");
        operation.setAmount(dto.getAmount());

        Account account = accountRepository.findAccountByNumber(dto.getAccountNumber());
        if(account != null) {
            account.setBalance(account.getBalance() + dto.getAmount());
            operation.setAccount(accountRepository.save(account));
        } else {
            throw new BankTechnicalException("Le compte bancaire" + dto.getAccountNumber().toString() + " n'existe pas !!!");
        }

        OperationDto operationDto = mapToOperationDto(operationRepository.save(operation));
        // Log de sortie
        log.info("Sortie, OperationDto={}", operationDto);
        return operationDto;
    }

    @Override
    public OperationDto withdrawal(TransactionDto dto) throws BankTechnicalException {
        // Log d'entrée
        log.info("Entée, TransactionDto={}", dto);
        Operation operation = instanciateOperation("WITHDRAWAL");
        operation.setAmount(dto.getAmount());

        Account account = accountRepository.findAccountByNumber(dto.getAccountNumber());

        if(account != null) {
            account.setBalance(account.getBalance() - dto.getAmount());
            operation.setAccount(accountRepository.save(account));
        } else {
            throw new BankTechnicalException("Le compte bancaire" + dto.getAccountNumber().toString() + " n'existe pas!!!");
        }

        OperationDto operationDto = mapToOperationDto(operationRepository.save(operation));
        // Log de sortie
        log.info("Sortie, OperationDto={}", operationDto);
        return operationDto;
    }

    @Override
    public List<OperationDto> statement(Long accountNumber) {
        Account account = accountRepository.findAccountByNumber(accountNumber);
        List<Operation> list = operationRepository.findByAccount(account);

        return list.stream().map(this::mapToOperationDto).collect(Collectors.toList());
    }

    private Operation instanciateOperation(String type) {
        Operation operation = new Operation();
        operation.setType(type);
        operation.setDate(new Date());
        return operation;
    }

    private OperationDto mapToOperationDto(Operation savedOperation) {
        OperationDto dto = new OperationDto();
        dto.setAmount(savedOperation.getAmount());
        dto.setDate(savedOperation.getDate());
        dto.setType(savedOperation.getType());
        dto.setBalance(savedOperation.getAccount().getBalance());
        return dto;
    }
}
