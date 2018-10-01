package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.PricelistDTO;

public interface PricelistService {

    List<PricelistDTO> findAll();

    PricelistDTO findById(Long id);

    PricelistDTO create(PricelistDTO pricelistDTO);

    PricelistDTO update(Long id, PricelistDTO pricelistDTO);

    void delete(Long id);
}
