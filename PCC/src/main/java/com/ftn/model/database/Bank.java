package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jasmina on 10/12/2017.
 */
@Entity
@NoArgsConstructor
@Data
public class Bank {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String code; //IIN(Issuer Identification Number) or BIN

    @Column
    private String url;
}
