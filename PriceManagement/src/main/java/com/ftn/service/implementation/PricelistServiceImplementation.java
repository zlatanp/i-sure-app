package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.PricelistDTO;
import com.ftn.service.PricelistService;

@Service
public class PricelistServiceImplementation implements PricelistService{

	@Value("${dc.home}")
	private String dc_home;

    @Value("${dc.pricelist}")
    private String dc_pricelist;

	@Override
	public List<PricelistDTO> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PricelistDTO[]> response = restTemplate.getForEntity(dc_home + dc_pricelist, PricelistDTO[].class);

        return Arrays.asList(response.getBody());
	}

	@Override
	public PricelistDTO findById(Long id) {
		dc_pricelist += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PricelistDTO> response = restTemplate.getForEntity(dc_home + dc_pricelist, PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public PricelistDTO create(PricelistDTO pricelistDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PricelistDTO> response = restTemplate.postForEntity(dc_home + dc_pricelist, new HttpEntity<>(pricelistDTO), PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public PricelistDTO update(Long id, PricelistDTO pricelistDTO) {
		dc_pricelist += "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);
        
        ResponseEntity<PricelistDTO> response = restTemplate.exchange(dc_home + dc_pricelist, HttpMethod.PATCH, new HttpEntity<>(pricelistDTO), PricelistDTO.class);

        return response.getBody();
	}

	@Override
	public void delete(Long id) {
		dc_pricelist += "/" + id;
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(dc_home + dc_pricelist);
	}
	
	@Override
	public PricelistDTO findcurrentlyActive() {
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PricelistDTO> response = restTemplate.getForEntity(dc_home + dc_pricelist+"/currentlyActive", PricelistDTO.class);

        return response.getBody();
	}
	
	@Override
	public Date findMaxDateTo() {
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Date> response = restTemplate.getForEntity(dc_home + dc_pricelist+"/maxDateTo", Date.class);
        return response.getBody();
	}
}
