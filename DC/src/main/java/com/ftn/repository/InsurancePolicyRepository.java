package com.ftn.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

	public Optional<InsurancePolicy> findById(Long id);

	public List<InsurancePolicy> findAll();

	public InsurancePolicy save(InsurancePolicy insurancePolicy);

	public void delete(Long id);

	public List<InsurancePolicy> findByDateOfIssue(Date dateOfIssue);
	
	public List<InsurancePolicy> findByDateBecomeEffective(Date dateBecomeEffective);


}
