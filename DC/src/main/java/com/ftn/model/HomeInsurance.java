package com.ftn.model;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Where(clause = SqlConstants.ACTIVE)
public class HomeInsurance extends Base {

	@Column(nullable = false)
	private String ownerFirstName;
	
	@Column(nullable = false)
	private String ownerLastName;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String personalId;
	
	@Column(nullable = false)
	private double price;

	@ManyToMany
	private List<Risk> risks = new ArrayList<>();

    @OneToMany(mappedBy = "homeInsurance", cascade = CascadeType.ALL)
    private List<InsurancePolicy> insurancePolicies = new ArrayList<>();

	public HomeInsurance(BaseDTO baseDTO){
		super(baseDTO);
		
	}
	
	public void merge(HomeInsuranceDTO homeInsuranceDTO){
		this.ownerFirstName = homeInsuranceDTO.getOwnerFirstName();
		this.ownerLastName = homeInsuranceDTO.getOwnerLastName();
		this.address = homeInsuranceDTO.getAddress();
		this.personalId = homeInsuranceDTO.getPersonalId();
		this.price = homeInsuranceDTO.getPrice();
		homeInsuranceDTO.getRisks().forEach(riskDTO -> this.getRisks().add(riskDTO.construct()));
	}

}
