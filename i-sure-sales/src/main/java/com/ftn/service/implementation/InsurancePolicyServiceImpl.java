package com.ftn.service.implementation;

import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.service.InsurancePolicyService;
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
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    @Value("${dc.insurance.policy}")
    private String URI;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<InsurancePolicyDTO> readAll() {

        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(URI, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO) {

        ResponseEntity<InsurancePolicyDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(insurancePolicyDTO),
                InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InsurancePolicyDTO> response = restTemplate.exchange(URI + id, HttpMethod.PATCH,
                new HttpEntity<>(insurancePolicyDTO), InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {

        restTemplate.delete(URI + id);
    }

    @Override
    public InsurancePolicyDTO findById(Long id) {

        ResponseEntity<InsurancePolicyDTO> response = restTemplate.getForEntity(URI + id, InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public List<InsurancePolicyDTO> findByDateOfIssue(Date date) {

        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(URI + "byDateOfIssue/" + date, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<InsurancePolicyDTO> findByDateBecomeEffective(Date date) {

        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(URI + "byDateBecomeEffective/" + date, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
