package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.InternationalTravelInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InternationalTravelInsuranceDTO extends BaseDTO{

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@NotNull
	private int durationInDays;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@NotNull
	private int numberOfPersons;

	@NotNull
	private double price;
	
	private List<RiskDTO> risks = new ArrayList<>();

	private List<InsurancePolicyDTO> insurancePolicies;

	public InternationalTravelInsuranceDTO(InternationalTravelInsurance internationalTravelInsurance){
		this(internationalTravelInsurance, true);
	}

	public InternationalTravelInsuranceDTO(InternationalTravelInsurance internationalTravelInsurance, boolean cascade){
		super(internationalTravelInsurance);
		this.startDate = internationalTravelInsurance.getStartDate();
		this.endDate = internationalTravelInsurance.getEndDate();
		this.durationInDays = internationalTravelInsurance.getDurationInDays();
		this.numberOfPersons = internationalTravelInsurance.getNumberOfPersons();
		this.price = internationalTravelInsurance.getPrice();
		if(cascade){
			this.risks = internationalTravelInsurance.getRisks().stream().map(risk -> new RiskDTO(risk, false)).collect(Collectors.toList());
            this.insurancePolicies = internationalTravelInsurance.getInsurancePolicies().stream().map(insurancePolicy -> new InsurancePolicyDTO(insurancePolicy, false)).collect(Collectors.toList());
        }
	}
	
	public InternationalTravelInsurance construct(){
		InternationalTravelInsurance internationalTravelInsurance = new InternationalTravelInsurance(this);
		internationalTravelInsurance.setStartDate(this.startDate);
		internationalTravelInsurance.setEndDate(this.endDate);
		internationalTravelInsurance.setDurationInDays(this.durationInDays);
		internationalTravelInsurance.setNumberOfPersons(this.numberOfPersons);
		internationalTravelInsurance.setPrice(this.price);
		if(this.risks != null && this.risks.size() > 0){
			this.risks.forEach(riskDTO -> internationalTravelInsurance.getRisks().add(riskDTO.construct()));
		}
		if(this.insurancePolicies != null && this.insurancePolicies.size() > 0){
            this.insurancePolicies.forEach(insurancePolicyDTO -> internationalTravelInsurance.getInsurancePolicies().add(insurancePolicyDTO.construct()));
        }
		return internationalTravelInsurance;
	}
}
