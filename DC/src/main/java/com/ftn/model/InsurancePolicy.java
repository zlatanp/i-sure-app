package com.ftn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.CustomerDTO;
import com.ftn.model.dto.InsurancePolicyDTO;

import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "insurance_policy" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class InsurancePolicy extends Base {

    @Column
    private double totalPrice;

    @Column(nullable = false)
    private Date dateOfIssue;

    @Column(nullable = false)
    private Date dateBecomeEffective;

    @ManyToMany
    private List<Customer> customers = new ArrayList<>();

    @ManyToOne(optional = false)
    private InternationalTravelInsurance internationalTravelInsurance;

    @ManyToOne
    private HomeInsurance homeInsurance;

    @ManyToOne
    private RoadsideAssistanceInsurance roadsideAssistanceInsurance;

    public InsurancePolicy(BaseDTO baseDTO) {
        super(baseDTO);
    }

    public void merge(InsurancePolicyDTO insurancePolicyDTO) {
        this.dateOfIssue = insurancePolicyDTO.getDateOfIssue();
        this.dateBecomeEffective = insurancePolicyDTO.getDateBecomeEffective();
        this.internationalTravelInsurance = insurancePolicyDTO.getInternationalTravelInsurance().construct();
        if (insurancePolicyDTO.getHomeInsurance() != null) {
            this.homeInsurance = insurancePolicyDTO.getHomeInsurance().construct();
        }
        if (insurancePolicyDTO.getRoadsideAssistanceInsurance() != null) {
            this.roadsideAssistanceInsurance = insurancePolicyDTO.getRoadsideAssistanceInsurance().construct();
        }
        this.totalPrice = insurancePolicyDTO.getTotalPrice();
    }
}
