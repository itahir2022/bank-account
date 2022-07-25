package com.example.bankaccount.api;

import com.example.bankaccount.core.IOperationService;
import com.example.bankaccount.domain.dto.OperationDto;
import com.example.bankaccount.domain.dto.TransactionDto;
import com.example.bankaccount.domain.exception.BankTechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@Slf4j
public class OperationApi {

    @Autowired
    IOperationService operationService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<OperationDto>> statement(@PathVariable Long accountNumber) {
        // Log d'entrée
        log.info("Entée, accountNumber={}", accountNumber);

        List<OperationDto> dtos = operationService.statement(accountNumber);

        // Log de sortie
        log.info("Sortie, accountNumber={}, nbOperations={}", accountNumber, dtos.size());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/deposit")
    public ResponseEntity<Boolean> deposit(@RequestBody TransactionDto transactionDto) {
        // Log d'entrée
        log.info("Entée, transactionDto={}", transactionDto);

        ResponseEntity<Boolean> response;
        try {
            operationService.deposit(transactionDto);
            response = ResponseEntity.ok(true);
        } catch (BankTechnicalException bte) {
            response = ResponseEntity.status(400).body(false);
        }

        // Log de sortie
        log.info("Sortie, transactionDto={}", transactionDto);
        return response;
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<Boolean> withdrawal(@RequestBody TransactionDto transactionDto) {
        // Log d'entrée
        log.info("Entée, transactionDto={}", transactionDto);

        ResponseEntity<Boolean> response;

        try {
            operationService.withdrawal(transactionDto);
            response = ResponseEntity.ok(true);
        } catch (BankTechnicalException e) {
            response = ResponseEntity.status(400).body(false);
        }

        // Log de sortie
        log.info("Sortie, transactionDto={}", transactionDto);
        return response;
    }
}
