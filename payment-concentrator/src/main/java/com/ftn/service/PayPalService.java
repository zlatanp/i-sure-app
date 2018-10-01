package com.ftn.service;

import javax.servlet.http.HttpServletRequest;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PayPalService {
	public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO );

	PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO, HttpServletRequest request);
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

	void successPayment(String token);

	void cancelPayment(String token);
}
