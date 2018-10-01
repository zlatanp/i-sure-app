package com.ftn.service.implementation;

import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.service.RoadsideAssistanceInsuranceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RoadsideAssistanceInsuranceServiceImpl implements RoadsideAssistanceInsuranceService {

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.roadside.assistance.insurance}")
    private String dc_roadside_assistance_insurance;

    @Override
    public List<RoadsideAssistanceInsuranceDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RoadsideAssistanceInsuranceDTO create(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.postForEntity(dc_home + dc_roadside_assistance_insurance, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public RoadsideAssistanceInsuranceDTO update(Long id,
                                                 RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {
        dc_roadside_assistance_insurance += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.exchange(dc_home + dc_roadside_assistance_insurance, HttpMethod.PATCH, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        dc_roadside_assistance_insurance += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(dc_home + dc_roadside_assistance_insurance);
    }

    @Override
    public RoadsideAssistanceInsuranceDTO findById(Long id) {
        dc_roadside_assistance_insurance += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByPersonalId(String personalId) {
        dc_roadside_assistance_insurance += "/byPersonalId/" + personalId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByYearOfManufacture(String yearOfManufacture) {
        dc_roadside_assistance_insurance += "/byYearOfManufacture/" + yearOfManufacture;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByLicencePlateNumber(String licencePlateNumber) {
        dc_roadside_assistance_insurance += "/byLicencePlateNumber/" + licencePlateNumber;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByUndercarriageNumber(String undercarriageNumber) {
        dc_roadside_assistance_insurance += "/byUndercarriageNumber/" + undercarriageNumber;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByCarBrand(String carBrand) {
        dc_roadside_assistance_insurance += "/byCarBrand/" + carBrand;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByCarType(String carType) {
        dc_roadside_assistance_insurance += "/byCarType/" + carType;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_roadside_assistance_insurance, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
