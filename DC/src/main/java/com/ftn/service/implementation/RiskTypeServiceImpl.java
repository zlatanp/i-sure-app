package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.Risk;
import com.ftn.model.RiskType;
import com.ftn.model.dto.RiskDTO;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.repository.InsuranceCategoryRepository;
import com.ftn.repository.RiskRepository;
import com.ftn.repository.RiskTypeRepository;
import com.ftn.service.InsuranceCategoryService;
import com.ftn.service.RiskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class RiskTypeServiceImpl implements RiskTypeService{

    private final RiskTypeRepository riskTypeRepository;

    private final InsuranceCategoryRepository insuranceCategoryRepository;

    @Autowired
    public RiskTypeServiceImpl(RiskTypeRepository riskTypeRepository, InsuranceCategoryRepository insuranceCategoryRepository){
        this.riskTypeRepository = riskTypeRepository;
        this.insuranceCategoryRepository = insuranceCategoryRepository;
    }

    @Override
    public List<RiskTypeDTO> readAll() {
        return riskTypeRepository.findAll().stream().map(RiskTypeDTO::new).collect(Collectors.toList());
    }

    @Override
    public RiskTypeDTO create(RiskTypeDTO riskTypeDTO) {
        final RiskType riskType = riskTypeDTO.construct();
        riskTypeRepository.save(riskType);
        return new RiskTypeDTO(riskType);
    }

    @Override
    public RiskTypeDTO update(Long id, RiskTypeDTO riskTypeDTO) {
        final RiskType riskType = riskTypeRepository.findById(id).orElseThrow(NotFoundException::new);
        riskType.merge(riskTypeDTO);
        riskTypeRepository.save(riskType);
        return new RiskTypeDTO(riskType);
    }

    @Override
    public void delete(Long id) {
        final RiskType riskType = riskTypeRepository.findById(id).orElseThrow(NotFoundException::new);
        riskTypeRepository.delete(riskType);
    }

    @Override
    public RiskTypeDTO findById(Long id) {
        final RiskType riskType = riskTypeRepository.findById(id).orElseThrow(NotFoundException::new);
        return new RiskTypeDTO(riskType);
    }

    @Override
    public RiskTypeDTO findByName(String name) {
        final RiskType riskType = riskTypeRepository.findByRiskTypeName(name).orElseThrow(NotFoundException::new);
        return new RiskTypeDTO(riskType);
    }

    @Override
     public Map<String, List<RiskDTO>> findByCategory(String name) {
        final List<RiskTypeDTO> riskTypes = riskTypeRepository.findByInsuranceCategory(insuranceCategoryRepository.findByCategoryName(name).get())
                .stream().map(RiskTypeDTO::new).collect(Collectors.toList());
        return riskTypes.stream().collect(Collectors.toMap(RiskTypeDTO::getRiskTypeName, RiskTypeDTO::getRisks));
    }

    @Override
    public List<RiskTypeDTO> findRiskTypesByCategory(String name) {
        return riskTypeRepository.findByInsuranceCategory(insuranceCategoryRepository.findByCategoryName(name).get())
                .stream().map(RiskTypeDTO::new).collect(Collectors.toList());
    }
    
    
}
