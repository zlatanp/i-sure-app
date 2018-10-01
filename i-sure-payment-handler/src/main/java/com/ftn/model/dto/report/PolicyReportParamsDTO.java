package com.ftn.model.dto.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Jasmina on 23/01/2018.
 */
@Data
@NoArgsConstructor
public class PolicyReportParamsDTO {

    private boolean hasCar;

    private boolean hasHome;

    private double totalPrice;

    private String carBrand;

    private String carType;

    private String ownerCar;

    private String ownerCarId;

    private String licenceNum;

    private String undercarriageNum;

    private double carInsurancePrice;

    private double homeInsurancePrice;

    private String ownerHome;

    private String ownerHomeId;

    private String homeAddress;

    private double travelInsurancePrice;

    private Date expirationDate;

    private Date effectiveDate;

    private int numDays;

    private int numPerson;

    private Date issueDate;

    private long policyNumber;

}
