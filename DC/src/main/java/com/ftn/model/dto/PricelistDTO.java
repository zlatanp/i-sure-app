package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.Pricelist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PricelistDTO extends BaseDTO{

	//@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateFrom;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateTo;

	private List<PricelistItemDTO> pricelistItems = new ArrayList<>();
	
	public PricelistDTO(Pricelist pricelist) {
		this(pricelist, true);
	}
	
	public PricelistDTO(Pricelist pricelist, boolean cascade) {
		super(pricelist);
		this.dateFrom = pricelist.getDateFrom();
		this.dateTo = pricelist.getDateTo();
		if(cascade){
            this.pricelistItems = pricelist.getPricelistItems().stream().map(pricelistItem -> new PricelistItemDTO(pricelistItem, false)).collect(Collectors.toList());
        }
	}
	
	public Pricelist construct() {
		final Pricelist pricelist = new Pricelist(this);
		pricelist.setDateFrom(dateFrom);
		pricelist.setDateTo(dateTo);
		if(pricelistItems != null && pricelistItems.size() != 0) {
			pricelistItems.forEach(pricelistItemDTO -> pricelist.getPricelistItems().add(pricelistItemDTO.construct()));
		}
		return pricelist;
	}
}
