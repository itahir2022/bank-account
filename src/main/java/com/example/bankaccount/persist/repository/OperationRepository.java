package com.example.bankaccount.persist.repository;

import com.example.bankaccount.persist.entity.Account;
import com.example.bankaccount.persist.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccount(Account account);
}
