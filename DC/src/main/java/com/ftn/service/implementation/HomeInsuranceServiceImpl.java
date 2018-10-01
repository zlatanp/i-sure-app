package com.ftn.service.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.exception.NotFoundException;
import com.ftn.model.HomeInsurance;
import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.repository.HomeInsuranceRepository;
import com.ftn.service.HomeInsuranceService;

@Service
public class HomeInsuranceServiceImpl implements HomeInsuranceService {

    private final HomeInsuranceRepository homeInsuranceRepository;

    @Autowired
    public HomeInsuranceServiceImpl(HomeInsuranceRepository HomeInsuranceRepository){
        this.homeInsuranceRepository = HomeInsuranceRepository;
    }

    @Override
    public List<HomeInsuranceDTO> readAll() {
        return homeInsuranceRepository.findAll().stream().map(HomeInsuranceDTO::new).collect(Collectors.toList());
    }

    @Override
    public HomeInsuranceDTO create(HomeInsuranceDTO HomeInsuranceDTO) {
        final HomeInsurance homeInsurance = HomeInsuranceDTO.construct();
        homeInsuranceRepository.save(homeInsurance);
        return new HomeInsuranceDTO(homeInsurance);
    }

    @Override
    public HomeInsuranceDTO update(Long id, HomeInsuranceDTO homeInsuranceDTO) {
        final HomeInsurance homeInsurance = homeInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        homeInsurance.merge(homeInsuranceDTO);
        homeInsuranceRepository.save(homeInsurance);
        return new HomeInsuranceDTO(homeInsurance);
    }

    @Override
    public void delete(Long id) {
        final HomeInsurance homeInsurance = homeInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        homeInsurance.setActive(false);
        homeInsuranceRepository.save(homeInsurance);
    }

    @Override
    public HomeInsuranceDTO findById(Long id) {
        final HomeInsurance homeInsurance = homeInsuranceRepository.findById(id).orElseThrow(NotFoundException::new);
        return new HomeInsuranceDTO(homeInsurance);
    }

   
	@Override
	public List<HomeInsuranceDTO> findByPersonalId(String personalId) {
		
		return homeInsuranceRepository.findByPersonalId(personalId).stream().map(HomeInsuranceDTO::new).collect(Collectors.toList());
         
	}
}
