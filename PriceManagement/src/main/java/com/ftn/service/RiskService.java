package com.ftn.service;

import com.ftn.model.dto.RiskDTO;

import java.util.List;

/**
 * Created by zlatan on 25/11/2017.
 */
public interface RiskService {

    List<RiskDTO> readAll();

    RiskDTO[] create(RiskDTO riskDTO);

    RiskDTO update(Long id, RiskDTO riskDTO);

    void delete(Long id);

    RiskDTO findById(Long id);

    RiskDTO findByName(String name);

	List<RiskDTO> findByRiskType(Long id);
}
