package com.ftn.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.PaymentInquiryService;

@Service
public class PaymentInquiryServiceImpl implements PaymentInquiryService{

	@Value("${proxy.handler.url}")
    private String URI;
	
	@Value("${proxy.handler.inquiries}")
	private String inquiries;

    RestTemplate restTemplate = new RestTemplate();
    
	@Override
	public PaymentInquiryInfoDTO send(TransactionDTO transactionDTO) {
		 ResponseEntity<PaymentInquiryInfoDTO> response = restTemplate.postForEntity(URI+inquiries, new HttpEntity<>(transactionDTO),
	                PaymentInquiryInfoDTO.class);

	        return response.getBody();
	}

}
