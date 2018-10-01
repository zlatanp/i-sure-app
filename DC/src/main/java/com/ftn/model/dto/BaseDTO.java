package com.ftn.model.dto;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.Base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 21/11/2017.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseDTO {

    private long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updated;

    private boolean active = true;
    
    public BaseDTO(Base base) {
        this.id = base.getId();
        this.created = base.getCreated();
        this.updated = base.getUpdated();
        this.active = base.isActive();
    }
}
