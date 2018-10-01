package com.ftn.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.HomeInsuranceDTO;

public interface HomeInsuranceService {

	List<HomeInsuranceDTO> readAll();

	HomeInsuranceDTO create(HomeInsuranceDTO HomeInsuranceDTO);

	HomeInsuranceDTO update(Long id, HomeInsuranceDTO HomeInsuranceDTO);

	void delete(Long id);

	HomeInsuranceDTO findById(Long id);

	List<HomeInsuranceDTO> findByPersonalId(String personalId);
}
