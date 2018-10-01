package com.ftn.model.dto;

import com.ftn.model.InsuranceCategory;
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
public class InsuranceCategoryDTO extends BaseDTO{

    @NotNull
    private String categoryName;

    private List<RiskTypeDTO> riskTypes = new ArrayList<>();

    public InsuranceCategoryDTO(InsuranceCategory insuranceCategory){
        this(insuranceCategory, true);
    }

    public InsuranceCategoryDTO(InsuranceCategory insuranceCategory, boolean cascade){
        super(insuranceCategory);
        this.categoryName = insuranceCategory.getCategoryName();
        if(cascade){
            this.riskTypes = insuranceCategory.getRiskTypes().stream().map(riskType -> new RiskTypeDTO(riskType, false)).collect(Collectors.toList());
        }
    }

    public InsuranceCategory construct() {
        final InsuranceCategory insuranceCategory = new InsuranceCategory(this);
        insuranceCategory.setCategoryName(categoryName);
        if(riskTypes != null && riskTypes.size() != 0){
            riskTypes.forEach(riskTypeDTO -> insuranceCategory.getRiskTypes().add(riskTypeDTO.construct()));
        }
        return insuranceCategory;
    }
}
