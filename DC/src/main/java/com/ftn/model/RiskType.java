package com.ftn.model;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.util.SqlConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlatan on 25/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "risk_type" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class RiskType extends Base {

    @Column(nullable = false)
    private String riskTypeName;

    @ManyToOne(optional = false)
    private InsuranceCategory insuranceCategory;

    @OneToMany(mappedBy = "riskType", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Risk> risks = new ArrayList<>();

    public RiskType(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(RiskTypeDTO riskTypeDTO) {
        this.riskTypeName = riskTypeDTO.getRiskTypeName();
        this.insuranceCategory = riskTypeDTO.getInsuranceCategory().construct();
    }
}
