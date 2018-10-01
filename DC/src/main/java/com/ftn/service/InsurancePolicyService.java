package com.ftn.service;

import com.ftn.model.dto.InsurancePolicyDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface InsurancePolicyService {

    List<InsurancePolicyDTO> readAll();

    InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO);

    InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO);

    void delete(Long id);

    InsurancePolicyDTO findById(Long id);

    List<InsurancePolicyDTO> findByDateOfIssue(Date date);
    
    List<InsurancePolicyDTO> findByDateBecomeEffective(Date date);

}
