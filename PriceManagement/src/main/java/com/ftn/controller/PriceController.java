package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.service.PriceService;

/**
 * Created by zlatan on 1/17/18.
 */
@Controller
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService){
        this.priceService = priceService;
    }

    @PostMapping
    public ResponseEntity getPrice(@RequestBody InsurancePolicyDTO insurancePolicyDTO) {
        return new ResponseEntity<>(priceService.getPrice(insurancePolicyDTO), HttpStatus.OK);
    }

}
