package com.ftn.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.InternationalTravelInsurance;

public interface InternationalTravelInsuranceRepository extends JpaRepository<InternationalTravelInsurance, Long> {

	Optional<InternationalTravelInsurance> findById(Long id);

	List<InternationalTravelInsurance> findAll();

	InternationalTravelInsurance save(InternationalTravelInsurance internationalTravelInsurance);

	void delete(Long id);

	List<InternationalTravelInsurance> findByStartDate(Date startDate);

	List<InternationalTravelInsurance> findByDurationInDays(int durationInDays);

	List<InternationalTravelInsurance> findByNumberOfPersons(int numberOfPerson);

}
