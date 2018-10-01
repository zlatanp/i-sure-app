package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InsurancePolicyDTO extends BaseDTO{

	private double totalPrice;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfIssue;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateBecomeEffective;
	
	private List<CustomerDTO> customers = new ArrayList<>();

	@NotNull
	private InternationalTravelInsuranceDTO internationalTravelInsurance;
	
	private HomeInsuranceDTO homeInsurance;

	private RoadsideAssistanceInsuranceDTO roadsideAssistanceInsurance;

}
