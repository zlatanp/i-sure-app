package com.ftn.repository;

import com.ftn.model.database.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 16/12/2017.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    Transaction findById(Long id);
}
