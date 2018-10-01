package com.ftn.service.implementation;

import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.service.InternationalTravelInsuranceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class InternationalTravelInsuranceServiceImpl implements InternationalTravelInsuranceService {

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.international.travel.insurance}")
    private String dc_international_travel_insurance;

    @Override
    public List<InternationalTravelInsuranceDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_international_travel_insurance, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InternationalTravelInsuranceDTO create(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.postForEntity(dc_home + dc_international_travel_insurance, new HttpEntity<>(internationalTravelInsuranceDTO), InternationalTravelInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public InternationalTravelInsuranceDTO update(Long id,
                                                  InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
        dc_international_travel_insurance += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.exchange(dc_home + dc_international_travel_insurance, HttpMethod.PATCH, new HttpEntity<>(internationalTravelInsuranceDTO), InternationalTravelInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        dc_international_travel_insurance += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(dc_home + dc_international_travel_insurance);
    }

    @Override
    public InternationalTravelInsuranceDTO findById(Long id) {
        dc_international_travel_insurance += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.getForEntity(dc_home + dc_international_travel_insurance, InternationalTravelInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public List<InternationalTravelInsuranceDTO> findByIssueDate(Date date) {
        dc_international_travel_insurance += "/byIssueDate/" + date;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_international_travel_insurance, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<InternationalTravelInsuranceDTO> findByNumberOfPersons(int numberOfPersons) {
        dc_international_travel_insurance += "/byNumberOfPersons/" + numberOfPersons;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_international_travel_insurance, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<InternationalTravelInsuranceDTO> findByDurationInDays(int durationInDays) {
        dc_international_travel_insurance += "/byDurationsInDays/" + durationInDays;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(dc_home + dc_international_travel_insurance, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
