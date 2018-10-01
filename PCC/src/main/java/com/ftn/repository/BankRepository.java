package com.ftn.repository;

import com.ftn.model.database.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 10/12/2017.
 */
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findBankByCode(String code);
}
