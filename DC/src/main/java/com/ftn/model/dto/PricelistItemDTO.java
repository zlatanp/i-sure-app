package com.ftn.model.dto;

import com.ftn.model.PricelistItem;

import com.ftn.model.Risk;
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
	
	public PricelistItemDTO(PricelistItem pricelistItem) {
		this(pricelistItem, true);
	}
	
	public PricelistItemDTO(PricelistItem pricelistItem, boolean cascade) {
		super(pricelistItem);
		this.coefficient = pricelistItem.getCoefficient();
		this.price = pricelistItem.getPrice();
		if(cascade){
			this.risk = new RiskDTO(pricelistItem.getRisk(), false);
		}
	}
	
	public PricelistItem construct() {
		final PricelistItem pricelistItem = new PricelistItem(this);
		pricelistItem.setCoefficient(coefficient);
		pricelistItem.setPrice(price);
		if(this.risk != null){
			pricelistItem.setRisk(risk.construct());
		}
		
		return pricelistItem;
	}
}
