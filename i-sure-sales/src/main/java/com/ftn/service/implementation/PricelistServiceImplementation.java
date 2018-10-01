package com.ftn.service.implementation;

import com.ftn.model.dto.PricelistDTO;
import com.ftn.service.PricelistService;
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
public class PricelistServiceImplementation implements PricelistService{

    @Value("${dc.pricelist}")
    private String URI;

    RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<PricelistDTO> findAll() {

        ResponseEntity<PricelistDTO[]> response = restTemplate.getForEntity(URI, PricelistDTO[].class);

        return Arrays.asList(response.getBody());
	}

	@Override
	public PricelistDTO findById(Long id) {

        ResponseEntity<PricelistDTO> response = restTemplate.getForEntity(URI + id, PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public PricelistDTO create(PricelistDTO pricelistDTO) {

        ResponseEntity<PricelistDTO> response = restTemplate.postForEntity(URI, new HttpEntity<>(pricelistDTO), PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public PricelistDTO update(Long id, PricelistDTO pricelistDTO) {

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);
        
        ResponseEntity<PricelistDTO> response = restTemplate.exchange(URI + id, HttpMethod.PATCH, new HttpEntity<>(pricelistDTO), PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public void delete(Long id) {

	    restTemplate.delete(URI + id);
	}
}
