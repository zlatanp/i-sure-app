package com.ftn.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

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
}
