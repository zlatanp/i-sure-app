package com.ftn.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ftn.model.HomeInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HomeInsuranceDTO extends BaseDTO {

	@NotNull
	@Size(max = 80)
	private String ownerFirstName;

	@NotNull
	@Size(max = 80)
	private String ownerLastName;

	@NotNull
	@Size(max = 80)
	private String address;

	@NotNull
	@Size(min = 13, max = 13)
	@Pattern(regexp = "[0-9]*")
	private String personalId;

	@NotNull
	private double price;

	private List<RiskDTO> risks = new ArrayList<>();

	private List<InsurancePolicyDTO> insurancePolicies;

	public HomeInsuranceDTO(HomeInsurance homeInsurance){
		this(homeInsurance, true);
	}
	
	public HomeInsuranceDTO(HomeInsurance homeInsurance, boolean cascade) {
		super(homeInsurance);
		this.ownerFirstName = homeInsurance.getOwnerFirstName();
		this.ownerLastName = homeInsurance.getOwnerLastName();
		this.address = homeInsurance.getAddress();
		this.personalId = homeInsurance.getPersonalId();
		this.price = homeInsurance.getPrice();
		if(cascade){
			this.risks = homeInsurance.getRisks().stream().map(risk -> new RiskDTO(risk, false)).collect(Collectors.toList());
            this.insurancePolicies = homeInsurance.getInsurancePolicies().stream().map(insurancePolicy -> new InsurancePolicyDTO(insurancePolicy, false)).collect(Collectors.toList());
		}
	}
	
	public HomeInsurance construct(){
		HomeInsurance homeInsurance = new HomeInsurance(this);
		homeInsurance.setOwnerFirstName(this.ownerFirstName);
		homeInsurance.setOwnerLastName(this.ownerLastName);
		homeInsurance.setAddress(this.address);
		homeInsurance.setPersonalId(this.personalId);
		homeInsurance.setPrice(this.price);
		if(this.risks != null && this.risks.size() > 0){
			this.risks.forEach(riskDTO -> homeInsurance.getRisks().add(riskDTO.construct()));
		}
		if(this.insurancePolicies != null && this.insurancePolicies.size() > 0){
		    this.insurancePolicies.forEach(insurancePolicyDTO -> homeInsurance.getInsurancePolicies().add(insurancePolicyDTO.construct()));
        }
		return homeInsurance;
	}
	
}
