package com.ftn.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoadsideAssistanceInsuranceDTO  extends BaseDTO{

	@NotNull
	@Size(max = 80)
	private String ownerFirstName;

	@NotNull
	@Size(max = 80)
	private String ownerLastName;

	@NotNull
	@Size(min = 13, max = 13)
    @Pattern(regexp = "[0-9]*")
	private String personalId;

	@NotNull
	@Size(max = 80)
	private String carBrand;
	
	@NotNull
	@Size(max = 80)
	private String carType;

	@NotNull
	@Size(min = 4, max = 4)
	@Pattern(regexp = "[0-9]*")
	private String yearOfManufacture;

	@NotNull
	@Size(min = 7, max = 7)
	@Pattern(regexp = "\\w*")
	private String licencePlateNumber;

	@NotNull
	@Size(min = 7, max = 7)
	@Pattern(regexp = "\\w*")
	private String undercarriageNumber;

	@NotNull
	private double price;

	private List<RiskDTO> risks = new ArrayList<>();

}
