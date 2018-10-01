package com.ftn.model.dto.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Jasmina on 22/01/2018.
 */
@Data
@NoArgsConstructor
public class ProbaReportDTO {

    private Long id;

    private Long risk_type_id;

    private String risk_name;

    private Date created;
}
