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
public class InsuranceCategoryDTO extends BaseDTO {

    @NotNull
    private String categoryName;

    private List<RiskTypeDTO> riskTypes = new ArrayList<>();

}
