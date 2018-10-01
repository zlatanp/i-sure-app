package com.ftn.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.PaymentDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Value("${dc.home}")
    private String home;
	
	@Value("${dc.payments}")
	private String payments;
	
	@Override
	public List<PaymentDTO> readAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentDTO[]> response = restTemplate.getForEntity(home + payments, PaymentDTO[].class);

        return Arrays.asList(response.getBody());
	}

	@Override
	public PaymentDTO create(PaymentDTO paymentDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentDTO> response = restTemplate.postForEntity(home + payments, new HttpEntity<>(paymentDTO),
        		PaymentDTO.class);

        return response.getBody();
	}

	@Override
	public PaymentDTO update(Long id, PaymentDTO paymentDTO) {
		String URI = home + payments + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<PaymentDTO> response = restTemplate.exchange(URI, HttpMethod.PATCH,
                new HttpEntity<>(paymentDTO), PaymentDTO.class);

        return response.getBody();

	}

	@Override
	public void delete(Long id) {
		String URI = home + payments + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI);
	}

	@Override
	public PaymentDTO findById(Long id) {
        String URI = home + payments + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentDTO> response = restTemplate.getForEntity(URI, PaymentDTO.class);

        return response.getBody();
	}
}
