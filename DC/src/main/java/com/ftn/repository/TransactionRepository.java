package com.ftn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.model.Transaction;


public interface TransactionRepository  extends JpaRepository<Transaction, Long>{
	public Optional<Transaction> findById(Long id);
	
	public Optional<Transaction> findByPaymentServiceId(String paymentId);
}
