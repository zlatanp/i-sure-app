package com.ftn.model.dto;

import com.ftn.model.Payment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaymentDTO extends BaseDTO{
	
	private String paymentUrl;
	private TransactionDTO transaction;
	
	public PaymentDTO(Payment payment) {
		super(payment);
		this.paymentUrl = payment.getPaymentUrl();
	}
	
	public Payment construct() {
		final Payment payment = new Payment(this);
		payment.setPaymentUrl(paymentUrl);
		return payment;
	}
}
