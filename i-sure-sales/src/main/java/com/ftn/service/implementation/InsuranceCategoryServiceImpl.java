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

    @Value("${dc.insurance.category}")
    private String URI;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<InsuranceCategoryDTO> readAll() {

        ResponseEntity<InsuranceCategoryDTO[]> response = restTemplate.getForEntity(URI, InsuranceCategoryDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public InsuranceCategoryDTO create(InsuranceCategoryDTO insuranceCategoryDTO) {

        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(insuranceCategoryDTO),
                InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public InsuranceCategoryDTO update(Long id, InsuranceCategoryDTO insuranceCategoryDTO) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.exchange(URI + id, HttpMethod.PATCH,
                new HttpEntity<>(insuranceCategoryDTO), InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {

        restTemplate.delete(URI + id);
    }

    @Override
    public InsuranceCategoryDTO findById(Long id) {

        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.getForEntity(URI + id, InsuranceCategoryDTO.class);

        return response.getBody();
    }

    @Override
    public InsuranceCategoryDTO findByName(String name) {

        ResponseEntity<InsuranceCategoryDTO> response = restTemplate.getForEntity(URI + "byPersonalId/" + name, InsuranceCategoryDTO.class);

        return response.getBody();
    }
}
