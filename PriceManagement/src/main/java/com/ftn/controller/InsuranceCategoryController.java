package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.InsuranceCategoryDTO;
import com.ftn.service.InsuranceCategoryService;
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
@RequestMapping("/insuranceCategories")
public class InsuranceCategoryController {

    private final InsuranceCategoryService insuranceCategoryService;

    @Autowired
    public InsuranceCategoryController(InsuranceCategoryService insuranceCategoryService){
        this.insuranceCategoryService = insuranceCategoryService;
    }

    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(insuranceCategoryService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody InsuranceCategoryDTO insuranceCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(insuranceCategoryService.create(insuranceCategoryDTO), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody InsuranceCategoryDTO insuranceCategoryDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(insuranceCategoryService.update(id, insuranceCategoryDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        insuranceCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(insuranceCategoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        return new ResponseEntity<>(insuranceCategoryService.findByName(name), HttpStatus.OK);
    }
}
