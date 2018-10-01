package com.ftn.repository;

import java.util.Optional;

import com.ftn.model.Payment;
import com.ftn.model.dto.PaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	Optional<Payment> findById(Long id);
}
