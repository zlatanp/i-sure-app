package com.ftn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.PricelistItemDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "pricelist_item" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class PricelistItem extends Base{
	
	@Column(nullable = false)
	private double coefficient;

	@Column(nullable = false)
	private double price;

	@ManyToOne
	private Risk risk;
	
	public PricelistItem(BaseDTO baseDTO) {
		super(baseDTO);
	}
	
	public PricelistItem(PricelistItem item) {
		this.setPrice(item.getPrice());
		this.setCoefficient(item.getCoefficient());
		this.setRisk(item.getRisk());
	}
	
	public void merge(PricelistItemDTO pricelistItemDTO) {
		this.coefficient = pricelistItemDTO.getCoefficient();
		this.price = pricelistItemDTO.getPrice();

	}
}
