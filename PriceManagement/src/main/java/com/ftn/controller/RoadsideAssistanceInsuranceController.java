package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.service.RoadsideAssistanceInsuranceService;
import com.ftn.service.implementation.RoadsideAssistanceInsuranceServiceImpl;

@RestController
@RequestMapping("/roadsideAssistanceInsurances")
public class RoadsideAssistanceInsuranceController {

	private final RoadsideAssistanceInsuranceService roadsideAssistanceInsuranceService;

    @Autowired
    public RoadsideAssistanceInsuranceController(RoadsideAssistanceInsuranceServiceImpl roadsideAssistanceInsuranceService){
        this.roadsideAssistanceInsuranceService = roadsideAssistanceInsuranceService;
    }

    
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(roadsideAssistanceInsuranceService.readAll(), HttpStatus.OK);
    }

    
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(roadsideAssistanceInsuranceService.create(roadsideAssistanceInsuranceDTO), HttpStatus.OK);
    }

    
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        
        try {
			roadsideAssistanceInsuranceDTO = roadsideAssistanceInsuranceService.update(id, roadsideAssistanceInsuranceDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        
        return new ResponseEntity<>(roadsideAssistanceInsuranceDTO, HttpStatus.OK);
    }

    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
			roadsideAssistanceInsuranceService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
    	
    	RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO;
    	
    	try {
			roadsideAssistanceInsuranceDTO = roadsideAssistanceInsuranceService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
        return new ResponseEntity<>(roadsideAssistanceInsuranceDTO, HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/byPersonalId/{personalId}")
    public ResponseEntity findByPersonalId(@PathVariable String personalId){
    	return new ResponseEntity<>(roadsideAssistanceInsuranceService.findByPersonalId(personalId), HttpStatus.OK);
    }

    
    @GetMapping(value = "/byYearOfManufacture/{yearOfManufacture}")
	public ResponseEntity findByYearOfManufacture(@PathVariable String yearOfManufacture){
    	return new ResponseEntity<>(roadsideAssistanceInsuranceService.findByYearOfManufacture(yearOfManufacture), HttpStatus.OK);
    }

    
    @GetMapping(value = "/byLicencePlateNumber/{licencePlateNumber}")
	public ResponseEntity findByLicencePlateNumber(@PathVariable String licencePlateNumber){
    	return new ResponseEntity<>(roadsideAssistanceInsuranceService.findByLicencePlateNumber(licencePlateNumber), HttpStatus.OK);
    }

    
    @GetMapping(value = "/byUndercarriageNumber/{undercarriageNumber}")
	public ResponseEntity findByUndercarriageNumber(@PathVariable String undercarriageNumber){
    	return new ResponseEntity<>(roadsideAssistanceInsuranceService.findByUndercarriageNumber(undercarriageNumber), HttpStatus.OK);
    }

    
    @GetMapping(value = "/byCarBrand/{carBrand}")
	public ResponseEntity findByCarBrand(@PathVariable String carBrand){
    	return new ResponseEntity<>(roadsideAssistanceInsuranceService.findByCarBrand(carBrand), HttpStatus.OK);
    }
	
    
    @GetMapping(value = "/byCarType/{carType}")
	public ResponseEntity findByCarType(@PathVariable String carType){
    	return new ResponseEntity<>(roadsideAssistanceInsuranceService.findByCarType(carType), HttpStatus.OK);
    }
	
}
