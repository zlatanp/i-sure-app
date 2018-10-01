package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.PaymentDTO;
import com.ftn.model.dto.TransactionDTO;

public interface PaymentService {
    List<PaymentDTO> readAll();

    PaymentDTO create(PaymentDTO paymentDTO);

    PaymentDTO update(Long id, PaymentDTO paymentDTO);

    void delete(Long id);

    PaymentDTO findById(Long id);
}
