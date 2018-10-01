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
import java.util.stream.Collectors;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class RiskServiceImpl implements RiskService {

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.risk}")
    private String dc_risk;

    @Override
    public List<RiskDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO[]> response = restTemplate.getForEntity(dc_home + dc_risk, RiskDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskDTO[] create(RiskDTO riskDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO[]> response = restTemplate.postForEntity(dc_home + dc_risk, new HttpEntity<>(riskDTO),
                RiskDTO[].class);

        return response.getBody();
    }

    @Override
    public RiskDTO update(Long id, RiskDTO riskDTO) {
        dc_risk += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskDTO> response = restTemplate.exchange(dc_home + dc_risk, HttpMethod.PATCH,
                new HttpEntity<>(riskDTO), RiskDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        dc_risk += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(dc_home + dc_risk);
    }

    @Override
    public RiskDTO findById(Long id) {
        dc_risk += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity(dc_home + dc_risk, RiskDTO.class);

        return response.getBody();
    }

    @Override
    public RiskDTO findByName(String name) {
       
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO> response = restTemplate.getForEntity( dc_home + dc_risk + "/byPersonalId/" + name, RiskDTO.class);

        return response.getBody();
    }
    
    @Override
    public List<RiskDTO> findByRiskType(Long id) {
    	RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RiskDTO[]> response = restTemplate.getForEntity( dc_home + dc_risk + "/byRiskType/" + id, RiskDTO[].class);

        return Arrays.asList(response.getBody());
    }
}
