package com.ftn.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

}
