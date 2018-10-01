package com.ftn.service;

import java.util.Date;
import java.util.List;

import com.ftn.model.dto.InternationalTravelInsuranceDTO;

public interface InternationalTravelInsuranceService {

	List<InternationalTravelInsuranceDTO> readAll();

    InternationalTravelInsuranceDTO create(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO);

    InternationalTravelInsuranceDTO update(Long id, InternationalTravelInsuranceDTO internationalTravelInsuranceDTO);

    void delete(Long id);

    InternationalTravelInsuranceDTO findById(Long id);

    List<InternationalTravelInsuranceDTO> findByIssueDate(Date date);
    
    List<InternationalTravelInsuranceDTO> findByNumberOfPersons(int numberOfPersons);
    
    List<InternationalTravelInsuranceDTO> findByDurationInDays(int durationInDays);
}
