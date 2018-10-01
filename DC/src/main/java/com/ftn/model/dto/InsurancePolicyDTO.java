package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.Customer;
import com.ftn.model.HomeInsurance;
import com.ftn.model.InsurancePolicy;
import com.ftn.model.InternationalTravelInsurance;
import com.ftn.model.RoadsideAssistanceInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InsurancePolicyDTO extends BaseDTO {

	@NotNull
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

	public InsurancePolicyDTO(InsurancePolicy insurancePolicy) {
		this(insurancePolicy, true);
	}

	public InsurancePolicyDTO(InsurancePolicy insurancePolicy, boolean cascade) {
		super(insurancePolicy);
		this.totalPrice = insurancePolicy.getTotalPrice();
		this.dateOfIssue = insurancePolicy.getDateOfIssue();
		this.dateBecomeEffective = insurancePolicy.getDateBecomeEffective();
		if (cascade) {
			this.customers = insurancePolicy.getCustomers().stream().map(customer -> new CustomerDTO(customer, false)).collect(Collectors.toList());
		    this.internationalTravelInsurance = new InternationalTravelInsuranceDTO(insurancePolicy.getInternationalTravelInsurance(), false);
		    if(insurancePolicy.getHomeInsurance() != null){
                this.homeInsurance = new HomeInsuranceDTO(insurancePolicy.getHomeInsurance(), false);
            }
		    if(insurancePolicy.getRoadsideAssistanceInsurance() != null){
                this.roadsideAssistanceInsurance = new RoadsideAssistanceInsuranceDTO(insurancePolicy.getRoadsideAssistanceInsurance(), false);
            }
		}
	}

	public InsurancePolicy construct() {
		final InsurancePolicy insurancePolicy = new InsurancePolicy(this);
		insurancePolicy.setTotalPrice(totalPrice);
		insurancePolicy.setDateOfIssue(dateOfIssue);
		insurancePolicy.setDateBecomeEffective(dateBecomeEffective);
        if(this.customers != null && this.customers.size() > 0){
            this.customers.forEach(customerDTO -> insurancePolicy.getCustomers().add(customerDTO.construct()));
        }
        if(this.internationalTravelInsurance != null){
            insurancePolicy.setInternationalTravelInsurance(internationalTravelInsurance.construct());
        }
        if(this.homeInsurance != null){
            insurancePolicy.setHomeInsurance(homeInsurance.construct());
        }
        if(this.roadsideAssistanceInsurance != null){
            insurancePolicy.setRoadsideAssistanceInsurance(roadsideAssistanceInsurance.construct());
        }
		return insurancePolicy;
	}
}
