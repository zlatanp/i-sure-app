package com.ftn.model.dto;

import com.ftn.model.RiskType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zlatan on 25/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RiskTypeDTO extends BaseDTO {

    @NotNull
    private String riskTypeName;

    @NotNull
    private InsuranceCategoryDTO insuranceCategory;

    private List<RiskDTO> risks = new ArrayList<>();

    public RiskTypeDTO(RiskType riskType){
        this(riskType, true);
    }

    public RiskTypeDTO(RiskType riskType, boolean cascade){
        super(riskType);
        this.riskTypeName = riskType.getRiskTypeName();
        if(cascade){
            this.insuranceCategory = new InsuranceCategoryDTO(riskType.getInsuranceCategory(), false);
            this.risks = riskType.getRisks().stream().map(risk -> new RiskDTO(risk, false)).collect(Collectors.toList());
        }
    }

    public RiskType construct(){
        final RiskType riskType = new RiskType(this);
        riskType.setRiskTypeName(riskTypeName);
        if(insuranceCategory != null){
            riskType.setInsuranceCategory(insuranceCategory.construct());
        }
        if(risks != null && risks.size() != 0){
            risks.forEach(riskDTO -> riskType.getRisks().add(riskDTO.construct()));
        }
        return riskType;
    }

}
