package com.example.bankaccount.persist.repository;

import com.example.bankaccount.persist.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByNumber(Long number);
}
