package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.Customer;
import com.ftn.model.dto.CustomerDTO;
import com.ftn.repository.CustomerRepository;
import com.ftn.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> readAll() {
        return customerRepository.findAll().stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        final Customer customer = customerDTO.construct();
        customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        final Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        customer.merge(customerDTO);
        customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    @Override
    public void delete(Long id) {
        final Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO findById(Long id) {
        final Customer customer = customerRepository.findById(id).orElseThrow(NotFoundException::new);
        return new CustomerDTO(customer);
    }

    @Override
    public CustomerDTO findByPersonalId(String personalId) {
        final Customer customer = customerRepository.findByPersonalId(personalId).orElseThrow(NotFoundException::new);
        return new CustomerDTO(customer);
    }
}
