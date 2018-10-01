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

    @Value("${dc.roadside.assistance.insurance}")
    private String URI;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<RoadsideAssistanceInsuranceDTO> readAll() {

        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RoadsideAssistanceInsuranceDTO create(RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public RoadsideAssistanceInsuranceDTO update(Long id,
                                                 RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.exchange(URI + id, HttpMethod.PATCH, new HttpEntity<>(roadsideAssistanceInsuranceDTO), RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {

        restTemplate.delete(URI + id);
    }

    @Override
    public RoadsideAssistanceInsuranceDTO findById(Long id) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO> response = restTemplate.getForEntity(URI + id, RoadsideAssistanceInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByPersonalId(String personalId) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI + "byPersonalId/" + personalId, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByYearOfManufacture(String yearOfManufacture) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI + "byYearOfManufacture/" + yearOfManufacture, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByLicencePlateNumber(String licencePlateNumber) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI + "byLicencePlateNumber/" + licencePlateNumber, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByUndercarriageNumber(String undercarriageNumber) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI + "byUndercarriageNumber/" + undercarriageNumber, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByCarBrand(String carBrand) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI + "byCarBrand/" + carBrand, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<RoadsideAssistanceInsuranceDTO> findByCarType(String carType) {

        ResponseEntity<RoadsideAssistanceInsuranceDTO[]> response = restTemplate.getForEntity(URI + "byCarBrand/" + carType, RoadsideAssistanceInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
