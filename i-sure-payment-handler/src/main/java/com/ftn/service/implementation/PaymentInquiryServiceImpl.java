package com.ftn.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.service.PaymentInquiryService;

@Service
public class PaymentInquiryServiceImpl implements PaymentInquiryService{
	
	@Value("${merchant.id}")
    private String merchant_id;
	
	@Value("${merchant.password}")
	private String merchant_password;
	
	@Override
	public PaymentInquiryDTO create(TransactionDTO transactionDTO) {
        PaymentInquiryDTO paymentInquiryDTO = new PaymentInquiryDTO();
        paymentInquiryDTO.setMerchantId(merchant_id);
        paymentInquiryDTO.setMerchantPassword(merchant_password);
        paymentInquiryDTO.setPaymentType(transactionDTO.getPaymentType().getLabel());

        //TODO: Uncomment and delete
        //paymentInquiryDTO.setAmount(transactionDTO.getInsurancePolicy().getTotalPrice());
        //paymentInquiryDTO.setAmount(211.99);
        paymentInquiryDTO.setAmount(1.0);

        paymentInquiryDTO.setMerchantOrderId(transactionDTO.getId());
        paymentInquiryDTO.setMerchantTimestamp(transactionDTO.getTimestamp());
        paymentInquiryDTO.setErrorUrl("neka stranica u hendleru");

		return paymentInquiryDTO;
	}

}
