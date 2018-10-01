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
public class RiskDTO extends BaseDTO {

    @NotNull
    private String riskName;

    @NotNull
    private RiskTypeDTO riskType;

    private List<PricelistItemDTO> pricelistItem = new ArrayList<>();
}
