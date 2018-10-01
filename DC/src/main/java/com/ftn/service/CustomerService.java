package com.ftn.service;

import com.ftn.model.Customer;
import com.ftn.model.dto.CustomerDTO;

import java.util.List;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface CustomerService {

    List<CustomerDTO> readAll();

    CustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO update(Long id, CustomerDTO customerDTO);

    void delete(Long id);

    CustomerDTO findById(Long id);

    CustomerDTO findByPersonalId(String personalId);
}
