package com.ftn.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TransactionDTO extends BaseDTO{

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date timestamp;

	private TransactionStatus status;
	
	@NotNull
	private PaymentTypeDTO paymentType;
	
	@NotNull
	private Double amount;

	@NotNull
	private InsurancePolicyDTO insurancePolicy;
	
	private Long acquirerOrderId;
	
	private Date acquirerTimestamp;
}
