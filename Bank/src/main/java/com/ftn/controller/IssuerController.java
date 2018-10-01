package com.ftn.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.IssuerService;

/**
 * Created by Jasmina on 04/12/2017.
 */
@RestController
@RequestMapping("/issuer")
@CrossOrigin(origins = "*")
public class IssuerController {

    @Autowired
    private EnvironmentProperties environmentProperties;

    private final IssuerService issuerService;

    private final RestTemplate restTemplate;
    
    private Logger logger;


    @Autowired
    public IssuerController(IssuerService issuerService) {
        this.issuerService = issuerService;
        this.restTemplate = new RestTemplate();
    }

    @Transactional
    @PostMapping
    public ResponseEntity receiveOrderFromPCC(@Valid @RequestBody PaymentOrderDTO paymentOrderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();

        logger = LoggerFactory.getLogger(IssuerController.class);
        logger.info("Received order from PCC");

        PaymentResponseInfoDTO paymentResponseInfoDTO = issuerService.generateResponse(paymentOrderDTO);

        return new ResponseEntity(paymentResponseInfoDTO, HttpStatus.OK);
    }
}
