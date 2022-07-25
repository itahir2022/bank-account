package com.example.bankaccount.api;

import com.example.bankaccount.core.IOperationService;
import com.example.bankaccount.domain.dto.OperationDto;
import com.example.bankaccount.domain.dto.TransactionDto;
import com.example.bankaccount.domain.exception.BankTechnicalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OperationApiTest {

    @InjectMocks
    OperationApi operationApi;

    @Mock
    private IOperationService operationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should retrieve statement by account number")
    void statement_OK() {
        OperationDto operation = getOperationFixture();
        List<OperationDto> dtos = new ArrayList<>();
        dtos.add(operation);
        Mockito.when(this.operationService.statement(Mockito.anyLong())).thenReturn(dtos);

        final ResponseEntity<List<OperationDto>> response = operationApi.statement(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("DEPOSIT", response.getBody().get(0).getType());
    }

    @Test
    void deposit_ok() throws BankTechnicalException {
        OperationDto operation = getOperationFixture();
        Mockito.when(this.operationService.deposit(Mockito.any())).thenReturn(operation);

        final ResponseEntity<Boolean> response = operationApi.deposit(new TransactionDto(1000.0, 1001L));
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(true, response.getBody());
    }

    @Test
    void withdrawal_ok() throws BankTechnicalException {
        OperationDto operation = getOperationFixture();
        Mockito.when(this.operationService.withdrawal(Mockito.any())).thenReturn(operation);

        final ResponseEntity<Boolean> response = operationApi.withdrawal(new TransactionDto(1000.0, 1001L));
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(true, response.getBody());
    }

    private OperationDto getOperationFixture() {
        OperationDto operation = new OperationDto();
        operation.setBalance(4500.0);
        operation.setType("DEPOSIT");
        operation.setAmount(2000.0);
        operation.setDate(new Date());
        return operation;
    }
}