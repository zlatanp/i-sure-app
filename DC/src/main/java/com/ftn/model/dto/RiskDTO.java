package com.ftn.model.dto;

import com.ftn.model.Risk;
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
public class RiskDTO extends BaseDTO {

    @NotNull
    private String riskName;

    private RiskTypeDTO riskType;

    private List<PricelistItemDTO> pricelistItem;

    public RiskDTO(Risk risk){
        this(risk, true);
    }

    public RiskDTO(Risk risk, boolean cascade){
        super(risk);
        this.riskName = risk.getRiskName();
        if(cascade){
            this.riskType = new RiskTypeDTO(risk.getRiskType(), false);
            this.pricelistItem = risk.getPricelistItem().stream().map(pricelistItem -> new PricelistItemDTO(pricelistItem, false)).collect(Collectors.toList());
        }
    }

    public Risk construct() {
        final Risk risk = new Risk(this);
        risk.setRiskName(riskName);
        if(riskType != null){
            risk.setRiskType(riskType.construct());
        }
        if(pricelistItem != null && pricelistItem.size() != 0){
            pricelistItem.forEach(pricelistItemDTO -> risk.getPricelistItem().add(pricelistItemDTO.construct()));
        }
        return risk;
    }
}
