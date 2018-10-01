package com.ftn.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Override
	public String toString() {
		return "HomeInsurance: { ownerFirstName=\"" + ownerFirstName + "\"" + ", ownerLastName=" + ownerLastName + ", personalId=" + personalId + ", carBrand=" + carBrand + ", carType=" + carType + ", yearOfManufacture=" + yearOfManufacture + ", licencePlateNumber=" + licencePlateNumber + ", undercarriageNumber=" + undercarriageNumber + ", price=" + price + ", risks=" + risks + " }";
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + Objects.hashCode(this.ownerFirstName);
		hash = 59 * hash + Objects.hashCode(this.ownerLastName);
		hash = 59 * hash + Objects.hashCode(this.personalId);
		hash = 59 * hash + Objects.hashCode(this.carBrand);
		hash = 59 * hash + Objects.hashCode(this.carType);
		hash = 59 * hash + Objects.hashCode(this.yearOfManufacture);
		hash = 59 * hash + Objects.hashCode(this.licencePlateNumber);
		hash = 59 * hash + Objects.hashCode(this.undercarriageNumber);
		hash = 59 * hash + Objects.hashCode(this.price);
		hash = 59 * hash + Objects.hashCode(this.risks);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RoadsideAssistanceInsuranceDTO other = (RoadsideAssistanceInsuranceDTO) obj;
		if (!Objects.equals(this.ownerFirstName, other.ownerFirstName)) {
			return false;
		}
		if (!Objects.equals(this.ownerLastName, other.ownerLastName)) {
			return false;
		}
		if (!Objects.equals(this.personalId, other.personalId)) {
			return false;
		}
		if (!Objects.equals(this.carBrand, other.carBrand)) {
			return false;
		}
		if (!Objects.equals(this.yearOfManufacture, other.yearOfManufacture)) {
			return false;
		}
		if (!Objects.equals(this.licencePlateNumber, other.licencePlateNumber)) {
			return false;
		}
		if (!Objects.equals(this.undercarriageNumber, other.undercarriageNumber)) {
			return false;
		}
		if (!Objects.equals(this.price, other.price)) {
			return false;
		}
		if (!Objects.equals(this.risks, other.risks)) {
			return false;
		}
		return true;
	}

}
