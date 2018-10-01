package com.ftn.service.implementation;

import com.ftn.model.dto.RiskDTO;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.service.RiskTypeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class RiskTypeServiceImpl implements RiskTypeService {

    @Value("${proxy.dc.url}")
    private String URI;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<RiskTypeDTO> readAll() {

        ResponseEntity<RiskTypeDTO[]> response = restTemplate.getForEntity(URI, RiskTypeDTO[].class);

        return Arrays.asList(response.getBody());
    }

    @Override
    public RiskTypeDTO create(RiskTypeDTO riskTypeDTO) {

        ResponseEntity<RiskTypeDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(riskTypeDTO),
                RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO update(Long id, RiskTypeDTO riskTypeDTO) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<RiskTypeDTO> response = restTemplate.exchange(URI + id, HttpMethod.PATCH,
                new HttpEntity<>(riskTypeDTO), RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public void delete(Long id) {

        restTemplate.delete(URI + id);
    }

    @Override
    public RiskTypeDTO findById(Long id) {

        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(URI + id, RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public RiskTypeDTO findByName(String name) {

        ResponseEntity<RiskTypeDTO> response = restTemplate.getForEntity(URI + "/name/" + name, RiskTypeDTO.class);

        return response.getBody();
    }

    @Override
    public Map<String, List<RiskDTO>> findRiskTypesByCategory(String name) {
        ResponseEntity<RiskTypeDTO[]> response = restTemplate.getForEntity(URI + "/riskTypes/insuranceCategoryRiskTypes/" + name, RiskTypeDTO[].class);
        return Arrays.asList(response.getBody()).stream().collect(Collectors.toMap(RiskTypeDTO::getRiskTypeName, RiskTypeDTO::getRisks));
    }
}
