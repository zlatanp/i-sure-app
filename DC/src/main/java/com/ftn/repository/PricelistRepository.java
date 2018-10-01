package com.ftn.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.model.Pricelist;

public interface PricelistRepository extends JpaRepository<Pricelist, Long>{
	Optional<Pricelist> findById(Long id);
	
	@Query("select p from Pricelist p where ?1 between p.dateFrom and p.dateTo")
	Optional<Pricelist> findCurrentlyActive(Date todayWithZeroTime);
	
	@Query("select max(p.dateTo) from Pricelist p")
	public Date findMaxDateTo();
}
