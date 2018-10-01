package com.ftn.service;

import com.ftn.model.dto.HomeInsuranceDTO;

import java.util.List;

public interface HomeInsuranceService {

	List<HomeInsuranceDTO> readAll();

	HomeInsuranceDTO create(HomeInsuranceDTO HomeInsuranceDTO);

	HomeInsuranceDTO update(Long id, HomeInsuranceDTO HomeInsuranceDTO);

	void delete(Long id);

	HomeInsuranceDTO findById(Long id);

	List<HomeInsuranceDTO> findByPersonalId(String personalId);
}
