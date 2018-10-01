package com.ftn.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentDTO;
import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.PaymentInquiryService;
import com.ftn.service.PaymentService;
import com.ftn.service.TransactionService;
import com.ftn.service.implementation.TransactionServiceImpl;

@Controller
@CrossOrigin( origins = "*" )
@RequestMapping("/inquiries")
public class PaymentInquiryController {
	
	@Value("${pc.home}")
    private String pc_home;
	
	@Value("${pc.payment.inquiries}")
	private String pc_inquiries;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	private final TransactionService transactionService;
	private final PaymentService paymentService;
	private final PaymentInquiryService paymentInquiryService;
	Logger logger;

    @Autowired
    public PaymentInquiryController(TransactionServiceImpl transactionService,
    		PaymentInquiryService paymentInquiryService,
    		PaymentService paymentService){
        this.transactionService = transactionService;
        this.paymentInquiryService = paymentInquiryService;
        this.paymentService = paymentService;
        this.logger = LoggerFactory.getLogger(PaymentInquiryController.class);
    }
    
    @PostMapping
    public ResponseEntity sendPaymentInquiry(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        transactionDTO = transactionService.create(transactionDTO);

        PaymentInquiryDTO piDTO = paymentInquiryService.create(transactionDTO);
        
        ResponseEntity<PaymentInquiryInfoDTO> response = restTemplate.postForEntity(pc_home + pc_inquiries, new HttpEntity<>(piDTO),
                PaymentInquiryInfoDTO.class);
        
        PaymentDTO payment = new PaymentDTO();
        payment.setPaymentUrl(response.getBody().getPaymentUrl());
        payment = paymentService.create(payment);
        
        transactionDTO.setPayment(payment);
        transactionDTO.setPaymentId(response.getBody().getPaymentId());
        transactionService.update(transactionDTO.getId(), transactionDTO);
        logger.info("Payment inquiry sent");
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
}
