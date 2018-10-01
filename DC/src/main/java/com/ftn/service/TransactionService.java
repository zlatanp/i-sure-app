package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.CustomerDTO;
import com.ftn.model.dto.TransactionDTO;

public interface TransactionService {
    List<TransactionDTO> readAll();

    TransactionDTO create(TransactionDTO transactionDTO);

    TransactionDTO update(Long id, TransactionDTO transactionDTO);

    void delete(Long id);

    TransactionDTO findById(Long id);
    
    TransactionDTO findByPaymentServiceId(String paymentId);
}
