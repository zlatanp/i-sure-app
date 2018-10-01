package com.ftn.service.implementation;

import com.ftn.model.dto.InsuranceCategoryDTO;
import com.ftn.service.InsuranceCategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class InsuranceCategoryServiceImpl implements InsuranceCategoryService {

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.insurance.category}")
    private String dc_insurance_category;

    @Override
    public List<InsuranceCategoryDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO[]> response = restTemplate.getForEntity(dc_home + dc_insurance_category, InsuranceCategoryDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InsuranceCategoryDTO create(InsuranceCategoryDTO insuranceCategoryDTO) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.postForEntity(dc_home + dc_insurance_category, new HttpEntity<>(insuranceCategoryDTO),
                InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public InsuranceCategoryDTO update(Long id, InsuranceCategoryDTO insuranceCategoryDTO) {
        dc_insurance_category += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.exchange(dc_home + dc_insurance_category, HttpMethod.PATCH,
                new HttpEntity<>(insuranceCategoryDTO), InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        dc_insurance_category += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(dc_home + dc_insurance_category);
    }

    @Override
    public InsuranceCategoryDTO findById(Long id) {
        dc_insurance_category += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.getForEntity(dc_home + dc_insurance_category, InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public InsuranceCategoryDTO findByName(String name) {

        dc_insurance_category += "/byPersonalId/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.getForEntity(dc_home + dc_insurance_category, InsuranceCategoryDTO.class);

        return response.getBody();
    }
}
