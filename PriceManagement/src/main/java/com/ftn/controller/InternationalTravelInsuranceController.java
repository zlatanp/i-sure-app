package com.ftn.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.service.InternationalTravelInsuranceService;
import com.ftn.service.implementation.InternationalTravelInsuranceServiceImpl;

@Controller
@RequestMapping("/internationalTravelInsurances")
public class InternationalTravelInsuranceController {

	 private final InternationalTravelInsuranceService internationalTravelInsuranceService;

	    @Autowired
	    public InternationalTravelInsuranceController(InternationalTravelInsuranceServiceImpl internationalTravelInsuranceService){
	        this.internationalTravelInsuranceService = internationalTravelInsuranceService;
	    }

	     
	    @GetMapping
	    public ResponseEntity read() {
	        return new ResponseEntity<>(internationalTravelInsuranceService.readAll(), HttpStatus.OK);
	    }

	     
	    @PostMapping
	    public ResponseEntity create(@Valid @RequestBody InternationalTravelInsuranceDTO internationalTravelInsuranceDTO, BindingResult bindingResult) {
	        if (bindingResult.hasErrors())
	            throw new BadRequestException();
	        return new ResponseEntity<>(internationalTravelInsuranceService.create(internationalTravelInsuranceDTO), HttpStatus.OK);
	    }

	     
	    @PatchMapping(value = "/{id}")
	    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody InternationalTravelInsuranceDTO internationalTravelInsuranceDTO, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            throw new BadRequestException();
	        }
	        
	        try {
				internationalTravelInsuranceDTO = internationalTravelInsuranceService.update(id, internationalTravelInsuranceDTO);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	        
	        return new ResponseEntity<>(internationalTravelInsuranceDTO, HttpStatus.OK);
	    }

	     
	    @DeleteMapping(value = "/{id}")
	    public ResponseEntity delete(@PathVariable Long id) {
	        try {
				internationalTravelInsuranceService.delete(id);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	     
	    @GetMapping(value = "/{id}")
	    public ResponseEntity findById(@PathVariable Long id){
	    	
	    	InternationalTravelInsuranceDTO internationalTravelInsuranceDTO;
	    	
	    	try {
				internationalTravelInsuranceDTO = internationalTravelInsuranceService.findById(id);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	    	
	        return new ResponseEntity<>(internationalTravelInsuranceDTO, HttpStatus.OK);
	    }

	     
	    @GetMapping(value = "/byIssueDate/{date}")
	    public ResponseEntity findByIssueDate(@PathVariable Date date) {
		
	    	return new ResponseEntity<>(internationalTravelInsuranceService.findByIssueDate(date), HttpStatus.OK);
		}

	     
	    @GetMapping(value = "/byNumberOfPersons/{numberOfPersons}")
		public ResponseEntity findByNumberOfPersons(@PathVariable int numberOfPersons) {
	    	return new ResponseEntity<>(internationalTravelInsuranceService.findByNumberOfPersons(numberOfPersons), HttpStatus.OK);
		}

	     
	    @GetMapping(value = "/byDurationInDays/{durationInDays}")
		public ResponseEntity findByDurationInDays(@PathVariable int durationInDays) {
	    	return new ResponseEntity<>(internationalTravelInsuranceService.findByDurationInDays(durationInDays), HttpStatus.OK);
		}
	
}
