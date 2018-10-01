package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.Risk;
import com.ftn.model.RiskType;
import com.ftn.model.dto.RiskDTO;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.repository.RiskRepository;
import com.ftn.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by zlatan on 25/11/2017.
 */
@Service
public class RiskServiceImpl implements RiskService {

    private final RiskRepository riskRepository;

    @Autowired
    public RiskServiceImpl(RiskRepository riskRepository){
        this.riskRepository = riskRepository;
    }

    @Override
    public List<RiskDTO> readAll() {
        return riskRepository.findAll().stream().map(RiskDTO::new).collect(Collectors.toList());
    }

    @Override
    public RiskDTO create(RiskDTO riskDTO) {
        final Risk risk = riskDTO.construct();
        riskRepository.save(risk);
        return new RiskDTO(risk);
    }

    @Override
    public RiskDTO update(Long id, RiskDTO riskDTO) {
        final Risk risk = riskRepository.findById(id).orElseThrow(NotFoundException::new);
        risk.merge(riskDTO);
        riskRepository.save(risk);
        return new RiskDTO(risk);
    }

    @Override
    public void delete(Long id) {
        final Risk risk = riskRepository.findById(id).orElseThrow(NotFoundException::new);
        riskRepository.delete(risk);
    }

    @Override
    public RiskDTO findById(Long id) {
        final Risk risk = riskRepository.findById(id).orElseThrow(NotFoundException::new);
        return new RiskDTO(risk);
    }

    @Override
    public RiskDTO findByName(String name) {
        final Risk risk = riskRepository.findByRiskName(name).orElseThrow(NotFoundException::new);
        return new RiskDTO(risk);
    }
    
    @Override
    public List<RiskDTO> findByRiskType(Long id) {
        final List<RiskDTO> risks =  riskRepository.findByRiskType(id).stream().map(RiskDTO::new).collect(Collectors.toList());;
        return risks;
    }
}
