package com.ftn.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.InternationalTravelInsurance;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.repository.InternationalTravelInsuranceRepository;
import com.ftn.service.InternationalTravelInsuranceService;

@Service
public class InternationalTravelInsuranceServiceImpl implements InternationalTravelInsuranceService{

	private final InternationalTravelInsuranceRepository internationalTravelInsuranceRepository;

    @Autowired
    public InternationalTravelInsuranceServiceImpl(InternationalTravelInsuranceRepository internationalTravelInsuranceRepository){
        this.internationalTravelInsuranceRepository = internationalTravelInsuranceRepository;
    }

    @Override
    public List<InternationalTravelInsuranceDTO> readAll() {
        return internationalTravelInsuranceRepository.findAll().stream().map(InternationalTravelInsuranceDTO::new).collect(Collectors.toList());
    }

    @Override
    public InternationalTravelInsuranceDTO create(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
        final InternationalTravelInsurance internationalTravelInsurance = internationalTravelInsuranceDTO.construct();
        internationalTravelInsuranceRepository.save(internationalTravelInsurance);
        return new InternationalTravelInsuranceDTO(internationalTravelInsurance);
    }

    @Override
    public InternationalTravelInsuranceDTO update(Long id, InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
        final InternationalTravelInsurance internationalTravelInsurance = internationalTravelInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        internationalTravelInsurance.merge(internationalTravelInsuranceDTO);
        internationalTravelInsuranceRepository.save(internationalTravelInsurance);
        return new InternationalTravelInsuranceDTO(internationalTravelInsurance);
    }

    @Override
    public void delete(Long id) {
        final InternationalTravelInsurance internationalTravelInsurance = internationalTravelInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        internationalTravelInsurance.setActive(false);
        internationalTravelInsuranceRepository.save(internationalTravelInsurance);
    }

    @Override
    public InternationalTravelInsuranceDTO findById(Long id) {
        final InternationalTravelInsurance internationalTravelInsurance = internationalTravelInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        return new InternationalTravelInsuranceDTO(internationalTravelInsurance);
    }
	
	@Override
	public List<InternationalTravelInsuranceDTO> findByIssueDate(Date date) {
		 final List<InternationalTravelInsurance> internationalTravelInsurance = internationalTravelInsuranceRepository.findByStartDate(date);
	        return internationalTravelInsurance.stream().map(InternationalTravelInsuranceDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByNumberOfPersons(int numberOfPersons) {
		final List<InternationalTravelInsurance> internationalTravelInsurance = internationalTravelInsuranceRepository.findByNumberOfPersons(numberOfPersons);
        return internationalTravelInsurance.stream().map(InternationalTravelInsuranceDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<InternationalTravelInsuranceDTO> findByDurationInDays(int durationInDays) {
		final  List<InternationalTravelInsurance> internationalTravelInsurance = internationalTravelInsuranceRepository.findByDurationInDays(durationInDays);
        return internationalTravelInsurance.stream().map(InternationalTravelInsuranceDTO::new).collect(Collectors.toList());
	}
}
