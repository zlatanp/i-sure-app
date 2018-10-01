package com.ftn.service;

import com.ftn.model.dto.onlinepayment.*;

/**
 * Created by Jasmina on 04/12/2017.
 */
public interface AcquirerService {

    boolean checkInquiry(PaymentInquiryDTO paymentInquiryDTO);

    PaymentInquiryInfoDTO generateInquiryInfo(PaymentInquiryDTO paymentInquiryDTO);

    PaymentOrderDTO generateOrder(PaymentOrderDTO paymentOrderDTO, long paymentId);

    PaymentCheckoutDTO generateCheckout(PaymentResponseInfoDTO paymentResponseInfoDTO);

    String getBankName();

    double getAmountForPaymentId(long paymentId);
}
