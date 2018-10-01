package com.ftn.repository;

import com.ftn.model.InsuranceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zlatan on 25/11/2017.
 */
public interface InsuranceCategoryRepository extends JpaRepository<InsuranceCategory, Long> {

    Optional<InsuranceCategory> findById(Long id);

    Optional<InsuranceCategory> findByCategoryName(String name);
}
