package com.ftn.controller;

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
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.TransactionService;
import com.ftn.service.implementation.TransactionServiceImpl;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/transactions")
public class TransactionController {
	
	private final TransactionService transactionService;
	private Logger logger;
	
    @Autowired
    public TransactionController(TransactionServiceImpl transactionService){

        this.transactionService = transactionService;
        logger=LoggerFactory.getLogger(TransactionController.class);
    }
    
    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(transactionService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        logger.info("Transaction create");
        return new ResponseEntity<>(transactionService.create(transactionDTO), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        try {
        	logger.info("Transaction update");
			transactionDTO = transactionService.update(id, transactionDTO);
        } catch (Exception e) {
			e.printStackTrace();
			logger.error("Error:transaction update");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);

    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
			transactionService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
    	TransactionDTO transactionDTO;
    	try {
			transactionDTO = transactionService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }
    
    @Transactional
    @GetMapping(value = "/payment/{paymentId}")
    public ResponseEntity findByPaymentId(@PathVariable String paymentId){
    	TransactionDTO transactionDTO;
    	try {
			transactionDTO = transactionService.findByPaymentServiceId(paymentId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }
}
