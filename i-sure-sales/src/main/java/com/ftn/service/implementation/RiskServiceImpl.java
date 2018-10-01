package com.ftn.service.implementation;

import com.ftn.model.dto.RiskDTO;
import com.ftn.service.RiskService;
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
public class RiskServiceImpl implements RiskService {

    @Value("${dc.risk}")
    private String URI;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<RiskDTO> readAll() {

        ResponseEntity<RiskDTO[]> response = restTemplate.getForEntity(URI, RiskDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskDTO create(RiskDTO riskDTO) {

        ResponseEntity<RiskDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(riskDTO),
                RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO update(Long id, RiskDTO riskDTO) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskDTO> response = restTemplate.exchange(URI + id, HttpMethod.PATCH,
                new HttpEntity<>(riskDTO), RiskDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {

        restTemplate.delete(URI + id);
    }

    @Override
    public RiskDTO findById(Long id) {

        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(URI + id, RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO findByName(String name) {

        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(URI + "byPersonalId/" + name, RiskDTO.class);

        return response.getBody();
    }
}
