package com.ftn.service;

import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.model.dto.TransactionDTO;

public interface PaymentInquiryService {
	PaymentInquiryInfoDTO send(TransactionDTO transactionDTO);
}
