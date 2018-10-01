package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.InsuranceCategory;
import com.ftn.model.dto.CustomerDTO;
import com.ftn.model.dto.InsuranceCategoryDTO;
import com.ftn.repository.InsuranceCategoryRepository;
import com.ftn.service.InsuranceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class InsuranceCategoryServiceImpl implements InsuranceCategoryService{

    private final InsuranceCategoryRepository insuranceCategoryRepository;

    @Autowired
    public InsuranceCategoryServiceImpl(InsuranceCategoryRepository insuranceCategoryRepository){
        this.insuranceCategoryRepository = insuranceCategoryRepository;
    }

    @Override
    public List<InsuranceCategoryDTO> readAll() {
        return insuranceCategoryRepository.findAll().stream().map(InsuranceCategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    public InsuranceCategoryDTO create(InsuranceCategoryDTO insuranceCategoryDTO) {
        final InsuranceCategory insuranceCategory = insuranceCategoryDTO.construct();
        insuranceCategoryRepository.save(insuranceCategory);
        return new InsuranceCategoryDTO(insuranceCategory);
    }

    @Override
    public InsuranceCategoryDTO update(Long id, InsuranceCategoryDTO insuranceCategoryDTO) {
        final InsuranceCategory insuranceCategory = insuranceCategoryRepository.findById(id).orElseThrow(NotFoundException::new);
        insuranceCategory.merge(insuranceCategoryDTO);
        insuranceCategoryRepository.save(insuranceCategory);
        return new InsuranceCategoryDTO(insuranceCategory);
    }

    @Override
    public void delete(Long id) {
        final InsuranceCategory insuranceCategory = insuranceCategoryRepository.findById(id).orElseThrow(NotFoundException::new);
        insuranceCategoryRepository.delete(insuranceCategory);
    }

    @Override
    public InsuranceCategoryDTO findById(Long id) {
        final InsuranceCategory insuranceCategory = insuranceCategoryRepository.findById(id).orElseThrow(NotFoundException::new);
        return new InsuranceCategoryDTO(insuranceCategory);
    }

    @Override
    public InsuranceCategoryDTO findByName(String name) {
        final InsuranceCategory insuranceCategory = insuranceCategoryRepository.findByCategoryName(name).orElseThrow(NotFoundException::new);
        return new InsuranceCategoryDTO(insuranceCategory);
    }
}
