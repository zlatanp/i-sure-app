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

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.insurance.policy}")
    private String dc_insurance_policy;

    @Override
    public List<InsurancePolicyDTO> readAll() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(dc_home + dc_insurance_policy, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InsurancePolicyDTO create(InsurancePolicyDTO insurancePolicyDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO> response = restTemplate.postForEntity(dc_home + dc_insurance_policy, new HttpEntity<>(insurancePolicyDTO),
                InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public InsurancePolicyDTO update(Long id, InsurancePolicyDTO insurancePolicyDTO) {
        dc_insurance_policy += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InsurancePolicyDTO> response = restTemplate.exchange(dc_home + dc_insurance_policy, HttpMethod.PATCH,
                new HttpEntity<>(insurancePolicyDTO), InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        dc_insurance_policy += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(dc_home + dc_insurance_policy);
    }

    @Override
    public InsurancePolicyDTO findById(Long id) {
        dc_insurance_policy += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO> response = restTemplate.getForEntity(dc_home + dc_insurance_policy, InsurancePolicyDTO.class);

        return response.getBody();
    }

    @Override
    public List<InsurancePolicyDTO> findByDateOfIssue(Date date) {
        dc_insurance_policy += "/byDateOfIssue/" + date;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(dc_home + dc_insurance_policy, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public List<InsurancePolicyDTO> findByDateBecomeEffective(Date date) {
        dc_insurance_policy += "/byDateBecomeEffective/" + date;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsurancePolicyDTO[]> response = restTemplate.getForEntity(dc_home + dc_insurance_policy, InsurancePolicyDTO[].class);

        return Arrays.asList(response.getBody());
    }


}
