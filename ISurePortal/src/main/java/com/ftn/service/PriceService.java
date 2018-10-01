package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.InsurancePolicyDTO;

public interface PriceService {
	
	List<Double> getPrice(InsurancePolicyDTO insurancePolicyDTO);

}
