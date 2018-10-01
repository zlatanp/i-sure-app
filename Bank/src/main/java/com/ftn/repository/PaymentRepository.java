package com.ftn.repository;

import com.ftn.model.database.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 16/12/2017.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findById(long id);
}
