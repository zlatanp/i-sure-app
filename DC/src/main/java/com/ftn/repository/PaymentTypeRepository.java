package com.ftn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.InsuranceCategory;
import com.ftn.model.PaymentType;
import com.ftn.model.RiskType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

    Optional<PaymentType> findById(Long id);

    Optional<PaymentType> findByLabel(String label);

}
