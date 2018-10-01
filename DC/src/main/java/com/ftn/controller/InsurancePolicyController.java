package com.ftn.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.InsurancePolicyService;
import com.ftn.service.TransactionService;
import com.ftn.service.implementation.InsurancePolicyServiceImpl;


/**
 * Created by Jasmina on 22/11/2017.
 */

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/insurancePolicies")
public class InsurancePolicyController {

    private final InsurancePolicyService insurancePolicyService;

    private final TransactionService transactionService;
    
    private final Logger logger;

    @Autowired
    public InsurancePolicyController(InsurancePolicyServiceImpl insurancePolicyService,
                                     TransactionService transactionService) {
        this.transactionService = transactionService;
        this.insurancePolicyService = insurancePolicyService;
        logger=LoggerFactory.getLogger(InsurancePolicyController.class);
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(insurancePolicyService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        logger.info("Insurance policy created");
        return new ResponseEntity<>(insurancePolicyService.create(insurancePolicyDTO), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        try {

            insurancePolicyDTO = insurancePolicyService.update(id, insurancePolicyDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(insurancePolicyDTO, HttpStatus.OK);

    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
            insurancePolicyService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {


        InsurancePolicyDTO insurancePolicyDTO;

        try {
            insurancePolicyDTO = insurancePolicyService.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(insurancePolicyDTO, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/byDateOfIssue/{date}")
    public ResponseEntity findByDateOfIssue(@PathVariable String date) {

        SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");

        Date dt;
        try {
            dt = sm.parse(date);
            return new ResponseEntity<>(insurancePolicyService.findByDateOfIssue(dt), HttpStatus.OK);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    @Transactional
    @GetMapping(value = "/transaction/{id}")
    public ResponseEntity findByTransactionId(@PathVariable long id) {
        TransactionDTO transactionDTO = transactionService.findById(id);
        InsurancePolicyDTO insurancePolicyDTO = transactionDTO.getInsurancePolicy();
        return new ResponseEntity<>(insurancePolicyDTO, HttpStatus.OK);
    }

}
