package com.ftn.service;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;

public interface AcquirerService {
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO);
}
