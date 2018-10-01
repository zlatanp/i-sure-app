package com.ftn.repository;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.HomeInsurance;

@Repository
public interface HomeInsuranceRepository extends JpaRepository<HomeInsurance, Long>{
	
	Optional<HomeInsurance> findById(Long id);

	List<HomeInsurance> findAll();

	HomeInsurance save(HomeInsurance homeInsurance);

	void delete(Long id);

	List<HomeInsurance> findByPersonalId(String personalId);
}
