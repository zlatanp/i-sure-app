package com.ftn.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.PaymentTypeDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "payment_type" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class PaymentType extends Base{
	
	@Column(nullable = false)
	private String label;
	
	@OneToMany(mappedBy = "paymentType")
	private List<Transaction> transactions;
	
	public PaymentType(BaseDTO baseDTO) {
		super(baseDTO);
	}
	
	public void merge(PaymentTypeDTO paymentTypeDTO) {
		this.label = paymentTypeDTO.getLabel();
	}
}
