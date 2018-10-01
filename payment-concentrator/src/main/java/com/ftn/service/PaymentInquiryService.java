package com.ftn.service;

import javax.servlet.http.HttpServletRequest;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;

public interface PaymentInquiryService {
	
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO, HttpServletRequest request);
}
