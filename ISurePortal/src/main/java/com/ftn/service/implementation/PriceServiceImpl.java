package com.ftn.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	
	@Value("${proxy.priceman.url}")
    private String URI;

    RestTemplate restTemplate = new RestTemplate();


	@Override
	public List<Double> getPrice(InsurancePolicyDTO insurancePolicyDTO) {
		ResponseEntity<List> response = restTemplate.postForEntity(URI+"/price", new HttpEntity<>(insurancePolicyDTO),
                List.class);
        return response.getBody();
	}

}
