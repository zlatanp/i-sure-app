package com.ftn.service;

import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;

import java.util.List;

public interface RoadsideAssistanceInsuranceService {

    List<RoadsideAssistanceInsuranceDTO> readAll();

    RoadsideAssistanceInsuranceDTO create(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO);

    RoadsideAssistanceInsuranceDTO update(Long id, RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO);

    void delete(Long id);

    RoadsideAssistanceInsuranceDTO findById(Long id);

    List<RoadsideAssistanceInsuranceDTO> findByPersonalId(String personalId);

    List<RoadsideAssistanceInsuranceDTO> findByYearOfManufacture(String yearOfManufacture);

    List<RoadsideAssistanceInsuranceDTO> findByLicencePlateNumber(String licencePlateNumber);

    List<RoadsideAssistanceInsuranceDTO> findByUndercarriageNumber(String undercarriageNumber);

    List<RoadsideAssistanceInsuranceDTO> findByCarBrand(String carBrand);

    List<RoadsideAssistanceInsuranceDTO> findByCarType(String carType);
}
