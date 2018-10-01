package com.ftn.model;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.InsuranceCategoryDTO;
import com.ftn.util.SqlConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlatan on 25/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "insurance_category" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class InsuranceCategory extends Base{

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "insuranceCategory", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<RiskType> riskTypes = new ArrayList<>();

    public InsuranceCategory(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(InsuranceCategoryDTO insuranceCategoryDTO) {
        this.categoryName = insuranceCategoryDTO.getCategoryName();
    }
}
