package com.ftn.service;

import java.util.Date;
import java.util.List;

import com.ftn.model.dto.InsurancePolicyDTO;

public interface InsurancePolicyService {

	List<InsurancePolicyDTO> readAll();

    InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO);

    InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO);

    void delete(Long id);

    InsurancePolicyDTO findById(Long id);

    List<InsurancePolicyDTO> findByDateOfIssue(Date date);
    
    List<InsurancePolicyDTO> findByDateBecomeEffective(Date date);
}
