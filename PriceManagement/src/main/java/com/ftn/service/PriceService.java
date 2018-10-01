package com.ftn.service;

import com.ftn.model.dto.InsurancePolicyDTO;

import java.util.List;

/**
 * Created by zlatan on 1/17/18.
 */
public interface PriceService {

    List<Double> getPrice(InsurancePolicyDTO insurancePolicyDTO);

}
