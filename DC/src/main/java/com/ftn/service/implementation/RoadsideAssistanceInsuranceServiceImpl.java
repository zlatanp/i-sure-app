package com.ftn.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.RoadsideAssistanceInsurance;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.repository.RoadsideAssistanceInsuranceRepository;
import com.ftn.service.RoadsideAssistanceInsuranceService;

@Service
public class RoadsideAssistanceInsuranceServiceImpl implements RoadsideAssistanceInsuranceService{


    private final RoadsideAssistanceInsuranceRepository roadsideAssistanceInsuranceRepository;

    @Autowired
    public RoadsideAssistanceInsuranceServiceImpl(RoadsideAssistanceInsuranceRepository roadsideAssistanceInsuranceRepository){
        this.roadsideAssistanceInsuranceRepository = roadsideAssistanceInsuranceRepository;
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> readAll() {
        return roadsideAssistanceInsuranceRepository.findAll().stream().map(RoadsideAssistanceInsuranceDTO::new).collect(Collectors.toList());
    }

    @Override
    public RoadsideAssistanceInsuranceDTO create(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
        final RoadsideAssistanceInsurance roadsideAssistanceInsurance = roadsideAssistanceInsuranceDTO.construct();
        roadsideAssistanceInsuranceRepository.save(roadsideAssistanceInsurance);
        return new RoadsideAssistanceInsuranceDTO(roadsideAssistanceInsurance);
    }

    @Override
    public RoadsideAssistanceInsuranceDTO update(Long id, RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
        final RoadsideAssistanceInsurance roadsideAssistanceInsurance = roadsideAssistanceInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        roadsideAssistanceInsurance.merge(roadsideAssistanceInsuranceDTO);
        roadsideAssistanceInsuranceRepository.save(roadsideAssistanceInsurance);
        return new RoadsideAssistanceInsuranceDTO(roadsideAssistanceInsurance);
    }

    @Override
    public void delete(Long id) {
        final RoadsideAssistanceInsurance roadsideAssistanceInsurance = roadsideAssistanceInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        roadsideAssistanceInsurance.setActive(false);
        roadsideAssistanceInsuranceRepository.save(roadsideAssistanceInsurance);
    }

    @Override
    public RoadsideAssistanceInsuranceDTO findById(Long id) {
        final RoadsideAssistanceInsurance roadsideAssistanceInsurance = roadsideAssistanceInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        return new RoadsideAssistanceInsuranceDTO(roadsideAssistanceInsurance);
    }

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByPersonalId(String personalId) {
		return roadsideAssistanceInsuranceRepository.findByPersonalId(personalId).stream().map(RoadsideAssistanceInsuranceDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByYearOfManufacture(String yearOfManufacture) {
		return roadsideAssistanceInsuranceRepository.findByYearOfManufacture(yearOfManufacture).stream().map(RoadsideAssistanceInsuranceDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByLicencePlateNumber(String licencePlateNumber) {
		return roadsideAssistanceInsuranceRepository.findByLicencePlateNumber(licencePlateNumber).stream().map(RoadsideAssistanceInsuranceDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByUndercarriageNumber(String undercarriageNumber) {
		return roadsideAssistanceInsuranceRepository.findByUndercarriageNumber(undercarriageNumber).stream().map(RoadsideAssistanceInsuranceDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByCarBrand(String carBrand) {
		return roadsideAssistanceInsuranceRepository.findByCarBrand(carBrand).stream().map(RoadsideAssistanceInsuranceDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<RoadsideAssistanceInsuranceDTO> findByCarType(String carType) {
		return roadsideAssistanceInsuranceRepository.findByCarType(carType).stream().map(RoadsideAssistanceInsuranceDTO::new).collect(Collectors.toList());
	}

	
}
