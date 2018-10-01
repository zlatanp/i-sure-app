package com.ftn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.model.RoadsideAssistanceInsurance;

public interface RoadsideAssistanceInsuranceRepository extends JpaRepository<RoadsideAssistanceInsurance, Long>{
	
	Optional<RoadsideAssistanceInsurance> findById(Long id);

	List<RoadsideAssistanceInsurance> findAll();

	RoadsideAssistanceInsurance save(RoadsideAssistanceInsurance roadsideAssistanceInsurance);

	void delete(Long id);

	List<RoadsideAssistanceInsurance> findByPersonalId(String personalId);

	List<RoadsideAssistanceInsurance> findByYearOfManufacture(String yearOfManifacture);

	List<RoadsideAssistanceInsurance> findByLicencePlateNumber(String licencePlateNumber);

	List<RoadsideAssistanceInsurance> findByUndercarriageNumber(String undercarriageNumber);

	List<RoadsideAssistanceInsurance> findByCarBrand(String carBrand);
	
	List<RoadsideAssistanceInsurance> findByCarType(String carType);

}
