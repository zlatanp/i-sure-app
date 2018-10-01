package com.ftn.service;

import java.util.Date;
import java.util.List;

import com.ftn.model.dto.PricelistDTO;

public interface PricelistService {
    List<PricelistDTO> readAll();

    PricelistDTO create(PricelistDTO pricelistDTO);

    PricelistDTO update(Long id, PricelistDTO pricelistDTO);

    void delete(Long id);

    PricelistDTO findById(Long id);

	PricelistDTO findcurrentlyActive();
	
	Date findMaxDateTo();
}
