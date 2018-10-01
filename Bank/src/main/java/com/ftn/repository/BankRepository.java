package com.ftn.repository;

import com.ftn.model.database.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zlatan on 6/24/17.
 */
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findById(Long id);
}
