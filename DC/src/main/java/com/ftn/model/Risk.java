package com.ftn.model;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.RiskDTO;
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
@SQLDelete(sql = SqlConstants.UPDATE + "risk" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Risk extends Base {

    @Column(nullable = false)
    private String riskName;

    @ManyToOne
    private RiskType riskType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "risk")
    private List<PricelistItem> pricelistItem = new ArrayList<>();

    public Risk(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(RiskDTO riskDTO) {
        this.riskName = riskDTO.getRiskName();
        this.riskType = riskDTO.getRiskType().construct();
    }
}
