package com.ftn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.PricelistDTO;
import com.ftn.model.dto.PricelistItemDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "pricelist" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Pricelist extends Base{

	@Column(nullable = false)
	private Date dateFrom;

	@Column(nullable = false)
	private Date dateTo;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "pricelist_id")
	private List<PricelistItem> pricelistItems = new ArrayList<>();
	
	public Pricelist(BaseDTO baseDTO) {
		super(baseDTO);
	}
	
	public void merge(PricelistDTO pricelistDTO) {
		this.dateFrom = pricelistDTO.getDateFrom();
		this.dateTo = pricelistDTO.getDateTo();
		this.pricelistItems.clear();
		if(pricelistDTO.getPricelistItems() != null && pricelistDTO.getPricelistItems().size() != 0) {
			for(PricelistItemDTO pricelistItem: pricelistDTO.getPricelistItems()) {
				this.pricelistItems.add(pricelistItem.construct());
			}
		}
	}
}
