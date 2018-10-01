package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Jasmina on 27/06/2017.
 */
@Entity
@NoArgsConstructor
@Data
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String accountNumber;

    @Column(unique = true)
    private String url;
}
