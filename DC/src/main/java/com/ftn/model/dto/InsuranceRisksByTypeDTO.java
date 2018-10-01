package com.ftn.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milica.govedarica on 12/15/2017.
 */
@Data
public class InsuranceRisksByTypeDTO {
    String riskType;
    List<RiskDTO> risks = new ArrayList<>();

    public InsuranceRisksByTypeDTO() {}

    public InsuranceRisksByTypeDTO(RiskTypeDTO riskType) {
        this.riskType = riskType.getRiskTypeName();
        this.risks = riskType.getRisks();
    }
}
