package com.ftn.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.PaymentDTO;
import com.ftn.service.PaymentService;
import com.ftn.service.ReportService;
import com.ftn.service.implementation.PaymentServiceImpl;

@Controller
@RequestMapping("/payments")
public class PaymentController {
	private final PaymentService paymentService;
    private final ReportService reportService;
    Logger logger;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService, ReportService reportService){
        this.reportService = reportService;
        this.paymentService = paymentService;
        this.logger = LoggerFactory.getLogger(PaymentController.class);
    }
    
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(paymentService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        logger.info("Payment created");
        return new ResponseEntity<>(paymentService.create(paymentDTO), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        try {
        	
        	paymentDTO = paymentService.update(id, paymentDTO);
        	logger.info("Payment updated");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error:payment update");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
        	paymentService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
    	
    	PaymentDTO paymentDTO;
    	
    	try {
    		paymentDTO = paymentService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }
}
