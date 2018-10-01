package com.ftn.repository;

import com.ftn.model.database.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zlatan on 6/24/17.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(Long id);

    Optional<Account> findByAccountNumber(String accountNumber);
}
