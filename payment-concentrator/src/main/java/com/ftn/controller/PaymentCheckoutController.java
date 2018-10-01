package com.ftn.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentCheckoutDTO;

@Controller
@RequestMapping("/checkout")
public class PaymentCheckoutController {

    @Value("${ph.home}")
    private String ph_home;

	@Value("${ph.payment.checkout}")
	private String ph_payment_checkout;

	Logger logger = LoggerFactory.getLogger(PaymentInquiryController.class);
	
	private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity receivePaymentCheckout(@Valid @RequestBody PaymentCheckoutDTO paymentCheckoutDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        String method = "";
        if(!paymentCheckoutDTO.getSuccessUrl().equals(null)) {
        	logger.info("Payment checkout success");
            method = "success";
        }else if(!paymentCheckoutDTO.getErrorUrl().equals(null)) {
        	logger.info("Payment checkout cancel");
            method = "cancel";
        }

        String url = ph_home + ph_payment_checkout + "/" + method;
        ResponseEntity<PaymentCheckoutDTO> response = restTemplate.postForEntity(url, new HttpEntity<>(paymentCheckoutDTO), PaymentCheckoutDTO.class);

        return new ResponseEntity(paymentCheckoutDTO, HttpStatus.OK);
    }
}
