package com.ftn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.model.Risk;

/**
 * Created by zlatan on 25/11/2017.
 */
public interface RiskRepository extends JpaRepository<Risk, Long> {

    Optional<Risk> findById(Long id);

    Optional<Risk> findByRiskName(String name);
    
	@Query("select r from Risk r where r.riskType.id = ?1")
	List<Risk> findByRiskType(Long id);
}
