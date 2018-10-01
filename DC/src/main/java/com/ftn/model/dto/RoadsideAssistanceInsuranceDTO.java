package com.ftn.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ftn.model.RoadsideAssistanceInsurance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoadsideAssistanceInsuranceDTO extends BaseDTO {

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

    // ENUMERACIJA?
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

    private List<InsurancePolicyDTO> insurancePolicies;

    public RoadsideAssistanceInsuranceDTO(RoadsideAssistanceInsurance roadsideAssistanceInsurance) {
        this(roadsideAssistanceInsurance, true);
    }

    public RoadsideAssistanceInsuranceDTO(RoadsideAssistanceInsurance roadsideAssistanceInsurance, boolean cascade) {
        super(roadsideAssistanceInsurance);
        this.ownerFirstName = roadsideAssistanceInsurance.getOwnerFirstName();
        this.ownerLastName = roadsideAssistanceInsurance.getOwnerLastName();
        this.personalId = roadsideAssistanceInsurance.getPersonalId();
        this.carBrand = roadsideAssistanceInsurance.getCarBrand();
        this.carType = roadsideAssistanceInsurance.getCarType();
        this.yearOfManufacture = roadsideAssistanceInsurance.getYearOfManufacture();
        this.licencePlateNumber = roadsideAssistanceInsurance.getLicencePlateNumber();
        this.undercarriageNumber = roadsideAssistanceInsurance.getUndercarriageNumber();
        this.price = roadsideAssistanceInsurance.getPrice();
        if (cascade) {
            this.risks = roadsideAssistanceInsurance.getRisks().stream().map(risk -> new RiskDTO(risk, false)).collect(Collectors.toList());
            this.insurancePolicies = roadsideAssistanceInsurance.getInsurancePolicies().stream().map(insurancePolicy -> new InsurancePolicyDTO(insurancePolicy, false)).collect(Collectors.toList());
        }
    }

    public RoadsideAssistanceInsurance construct() {
        RoadsideAssistanceInsurance roadsideAssistanceInsurance = new RoadsideAssistanceInsurance();
        roadsideAssistanceInsurance.setOwnerFirstName(this.ownerFirstName);
        roadsideAssistanceInsurance.setOwnerLastName(this.ownerLastName);
        roadsideAssistanceInsurance.setPersonalId(this.personalId);
        roadsideAssistanceInsurance.setCarBrand(this.carBrand);
        roadsideAssistanceInsurance.setCarType(this.carType);
        roadsideAssistanceInsurance.setYearOfManufacture(this.yearOfManufacture);
        roadsideAssistanceInsurance.setLicencePlateNumber(this.licencePlateNumber);
        roadsideAssistanceInsurance.setUndercarriageNumber(this.undercarriageNumber);
        roadsideAssistanceInsurance.setPrice(this.price);
        if (this.risks != null && this.risks.size() > 0) {
            this.risks.forEach(riskDTO -> roadsideAssistanceInsurance.getRisks().add(riskDTO.construct()));
        }
        if(this.insurancePolicies != null && this.insurancePolicies.size() > 0){
            this.insurancePolicies.forEach(insurancePolicyDTO -> roadsideAssistanceInsurance.getInsurancePolicies().add(insurancePolicyDTO.construct()));
        }
        return roadsideAssistanceInsurance;
    }

}
