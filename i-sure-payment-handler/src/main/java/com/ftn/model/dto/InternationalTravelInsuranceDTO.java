package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

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

}
