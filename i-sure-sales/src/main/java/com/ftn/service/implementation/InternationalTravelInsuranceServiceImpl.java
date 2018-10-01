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

    @Value("${dc.international.travel.insurance}")
    private String URI;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<InternationalTravelInsuranceDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(URI, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InternationalTravelInsuranceDTO create(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(internationalTravelInsuranceDTO), InternationalTravelInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public InternationalTravelInsuranceDTO update(Long id,
                                                  InternationalTravelInsuranceDTO internationalTravelInsuranceDTO) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH, new HttpEntity<>(internationalTravelInsuranceDTO), InternationalTravelInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI);
    }

    @Override
    public InternationalTravelInsuranceDTO findById(Long id) {
        URI += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO> response = restTemplate.getForEntity(URI, InternationalTravelInsuranceDTO.class);

        return response.getBody();
    }

    @Override
    public List<InternationalTravelInsuranceDTO> findByIssueDate(Date date) {
        URI += "/byIssueDate/" + date;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(URI, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<InternationalTravelInsuranceDTO> findByNumberOfPersons(int numberOfPersons) {
        URI += "/byNumberOfPersons/" + numberOfPersons;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(URI, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<InternationalTravelInsuranceDTO> findByDurationInDays(int durationInDays) {
        URI += "/byDurationsInDays/" + durationInDays;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InternationalTravelInsuranceDTO[]> response = restTemplate.getForEntity(URI, InternationalTravelInsuranceDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
