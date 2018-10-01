package com.ftn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.PaymentDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "payment" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Payment extends Base{
	
	@Column(nullable = false)
	private String paymentUrl;
	
	public Payment(BaseDTO baseDTO) {
		super(baseDTO);
	}
	
	public void merge(PaymentDTO paymentDTO) {
		this.paymentUrl = paymentDTO.getPaymentUrl();
	}
}
