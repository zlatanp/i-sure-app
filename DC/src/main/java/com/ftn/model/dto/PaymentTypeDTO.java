package com.ftn.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ftn.model.PaymentType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PaymentTypeDTO extends BaseDTO{
	@NotNull
	private String label;
	
	private List<TransactionDTO> transactions;
	
	public PaymentTypeDTO(PaymentType paymentType) {
		super(paymentType);
		this.label = paymentType.getLabel();
	}
	
	public PaymentType construct() {
		final PaymentType paymentType = new PaymentType(this);
		paymentType.setLabel(label);
		
		return paymentType;
	}
}
