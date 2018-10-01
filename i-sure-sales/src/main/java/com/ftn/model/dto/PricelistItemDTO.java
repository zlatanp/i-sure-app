package com.ftn.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PricelistItemDTO extends BaseDTO{

	@NotNull
	private double coefficient;

	@NotNull
	private double price;

	private RiskDTO risk;

}
