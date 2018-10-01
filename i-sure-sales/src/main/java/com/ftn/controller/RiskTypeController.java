package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.service.RiskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by zlatan on 25/11/2017.
 */
@RestController
@RequestMapping("/riskTypes")
public class RiskTypeController {

    private final RiskTypeService riskTypeService;

    @Autowired
    public RiskTypeController(RiskTypeService riskTypeService){
        this.riskTypeService = riskTypeService;
    }

    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(riskTypeService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RiskTypeDTO riskTypeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(riskTypeService.create(riskTypeDTO), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody RiskTypeDTO riskTypeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(riskTypeService.update(id, riskTypeDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        riskTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(riskTypeService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity findByBirthId(@PathVariable String name){
        return new ResponseEntity<>(riskTypeService.findByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/insuranceCategory/{name}")
    public ResponseEntity findRisksByCategory(@PathVariable String name){
        return new ResponseEntity<>(riskTypeService.findRiskTypesByCategory(name), HttpStatus.OK);
    }
}
