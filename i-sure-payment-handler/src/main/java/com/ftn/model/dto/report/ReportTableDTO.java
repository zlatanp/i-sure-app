package com.ftn.model.dto.report;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 23/01/2018.
 */
@Data
@NoArgsConstructor
public class ReportTableDTO {

    private String riskName;

    private String riskType;

    private double riskPrice;
}
