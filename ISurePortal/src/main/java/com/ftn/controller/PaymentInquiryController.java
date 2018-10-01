package com.ftn.controller;

import javax.validation.Valid;

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

import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.model.dto.TransactionStatus;
import com.ftn.service.PaymentInquiryService;

@Controller
@CrossOrigin( origins = "*" )
@RequestMapping("/inquiries")
public class PaymentInquiryController {
	
	private final PaymentInquiryService paymentInquiryService;

    @Autowired
    public PaymentInquiryController(PaymentInquiryService paymentInquiryService){
        this.paymentInquiryService = paymentInquiryService;
    }
    
    @PostMapping
    public ResponseEntity sendPaymentInquiry(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        PaymentInquiryInfoDTO paymentInquiryInfoDTO = paymentInquiryService.send(transactionDTO);
        return new ResponseEntity<>(paymentInquiryInfoDTO, HttpStatus.OK);
    }
}
