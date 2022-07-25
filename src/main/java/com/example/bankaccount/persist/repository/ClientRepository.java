package com.example.bankaccount.persist.repository;

import com.example.bankaccount.persist.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
