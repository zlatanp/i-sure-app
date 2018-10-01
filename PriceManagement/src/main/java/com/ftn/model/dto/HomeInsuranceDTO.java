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

	@Override
	public String toString() {
		return "HomeInsurance: { ownerFirstName=\"" + ownerFirstName + "\"" + ", ownerLastName=" + ownerLastName + ", address=" + address + ", personalId=" + personalId + ", price=" + price + ", risks=" + risks + " }";
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + Objects.hashCode(this.ownerFirstName);
		hash = 59 * hash + Objects.hashCode(this.ownerLastName);
		hash = 59 * hash + Objects.hashCode(this.address);
		hash = 59 * hash + Objects.hashCode(this.personalId);
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
		final HomeInsuranceDTO other = (HomeInsuranceDTO) obj;
		if (!Objects.equals(this.ownerFirstName, other.ownerFirstName)) {
			return false;
		}
		if (!Objects.equals(this.ownerLastName, other.ownerLastName)) {
			return false;
		}
		if (!Objects.equals(this.address, other.address)) {
			return false;
		}
		if (!Objects.equals(this.personalId, other.personalId)) {
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
