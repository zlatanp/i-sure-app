package com.ftn.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import com.ftn.model.dto.RiskDTO;
import com.ftn.service.RiskService;

/**
 * Created by zlatan on 25/11/2017.
 */
@RestController
@RequestMapping("/risks")
public class RiskController {

    private final RiskService riskService;
    private Logger logger;
    @Autowired
    public RiskController(RiskService riskService){
        this.riskService = riskService;
        logger=LoggerFactory.getLogger(RiskController.class);
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(riskService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RiskDTO riskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new BadRequestException();
        }
        
        try {
			riskService.create(riskDTO);
			logger.info("Risk created");
		} catch (Exception e) {
			System.out.println("Pukao create");
			logger.error("Error:create risk");
			e.printStackTrace();
		}
        List<RiskDTO> lista = riskService.findByRiskType(riskDTO.getRiskType().getId());
        System.out.println("Lista :"+lista);
        System.out.println("Broj clanova "+lista.size());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody RiskDTO riskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(riskService.update(id, riskDTO), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        riskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(riskService.findById(id), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/name/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        return new ResponseEntity<>(riskService.findByName(name), HttpStatus.OK);
    }
    
    @Transactional
    @GetMapping(value = "/byRiskType/{id}")
    public ResponseEntity findByRiskType(@PathVariable Long id){
        return new ResponseEntity<>(riskService.findByRiskType(id), HttpStatus.OK);
    }
}
