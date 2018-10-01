package com.ftn.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "transaction" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Transaction extends Base{
	
	@Column(nullable = false)
	private Date timestamp;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TransactionStatus status;
	
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;
	
	@Column(nullable = false)
	private Double amount;
	
	private String paymentServiceId;
	
	@OneToOne
	@JoinColumn(name = "insurance_policy_id")
	private InsurancePolicy insurancePolicy;
	
	private Long acquirerOrderId;
	
	private Date acquirerTimestamp;
	
	public Transaction(BaseDTO baseDTO) {
		super(baseDTO);
	}

	public void merge(TransactionDTO transactionDTO){
		this.timestamp = transactionDTO.getTimestamp();
		this.status = transactionDTO.getStatus();
		this.amount = transactionDTO.getAmount();
		this.acquirerOrderId = transactionDTO.getAcquirerOrderId();
		this.acquirerTimestamp = transactionDTO.getAcquirerTimestamp();
		this.paymentServiceId = transactionDTO.getPaymentId();

		if(transactionDTO.getPaymentType() != null){
			this.paymentType = transactionDTO.getPaymentType().construct();
		}
		if(transactionDTO.getInsurancePolicy() != null) {
			this.insurancePolicy = transactionDTO.getInsurancePolicy().construct();
		}
	}
}
