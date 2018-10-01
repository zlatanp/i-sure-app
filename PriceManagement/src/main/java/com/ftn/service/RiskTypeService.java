package com.ftn.service;

import com.ftn.model.dto.RiskTypeDTO;

import java.util.List;

/**
 * Created by zlatan on 25/11/2017.
 */
public interface RiskTypeService {

    List<RiskTypeDTO> readAll();

    RiskTypeDTO create(RiskTypeDTO riskTypeDTO);

    RiskTypeDTO update(Long id, RiskTypeDTO riskTypeDTO);

    void delete(Long id);

    RiskTypeDTO findById(Long id);

    RiskTypeDTO findByName(String name);
}
