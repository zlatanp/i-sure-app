package com.ftn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = SqlConstants.UPDATE + "insurance_policy" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class InternationalTravelInsurance extends Base{
	
	@Column
	private Date startDate;

	@Column
	private int durationInDays;

	@Column
	private Date endDate;

	@Column
	private int numberOfPersons;

	@Column
	private double price;

	@ManyToMany
	private List<Risk> risks = new ArrayList<>();

    @OneToMany(mappedBy = "internationalTravelInsurance", cascade = CascadeType.ALL)
    private List<InsurancePolicy> insurancePolicies = new ArrayList<>();
	
	public InternationalTravelInsurance(BaseDTO baseDTO){
		super(baseDTO);
	}
	
	public void merge(InternationalTravelInsuranceDTO internationalTravelInsuranceDTO){
		this.startDate = internationalTravelInsuranceDTO.getStartDate();
		this.endDate = internationalTravelInsuranceDTO.getEndDate();
		this.durationInDays = internationalTravelInsuranceDTO.getDurationInDays();
		this.numberOfPersons = internationalTravelInsuranceDTO.getNumberOfPersons();
		this.price = internationalTravelInsuranceDTO.getPrice();
		internationalTravelInsuranceDTO.getRisks().forEach(riskDTO -> this.getRisks().add(riskDTO.construct()));
	}
}
