package com.ftn.service;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.TransactionDTO;

public interface PaymentInquiryService {
	PaymentInquiryDTO create(TransactionDTO transactionDTO);
}
