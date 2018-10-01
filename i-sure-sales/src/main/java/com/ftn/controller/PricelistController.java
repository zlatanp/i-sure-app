package com.ftn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.model.dto.PricelistDTO;
import com.ftn.service.PricelistService;

@RestController
@RequestMapping("/pricelists")
public class PricelistController {
	
	private final PricelistService pricelistService;
	
	@Autowired
	public PricelistController(PricelistService pricelistService) {
		this.pricelistService = pricelistService;
	}
	
	@GetMapping
	public ResponseEntity read() {
		return new ResponseEntity<>(pricelistService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity readById(@PathVariable Long id) {
		return new ResponseEntity<>(pricelistService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity create(@RequestBody PricelistDTO pricelistDTO) {
		return new ResponseEntity<>(pricelistService.create(pricelistDTO), HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody PricelistDTO pricelistDTO) {
		return new ResponseEntity<>(pricelistService.update(id, pricelistDTO), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable Long id){
		pricelistService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
