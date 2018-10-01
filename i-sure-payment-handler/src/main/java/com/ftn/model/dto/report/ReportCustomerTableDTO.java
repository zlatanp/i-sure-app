package com.ftn.model.dto.report;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 23/01/2018.
 */
@Data
@NoArgsConstructor
public class ReportCustomerTableDTO {

    private String name;

    private String lastname;

    private String passport;

    private String jmbg;

    private String email;

    private String telephone;

    private boolean carrier;

    private String address;
}
