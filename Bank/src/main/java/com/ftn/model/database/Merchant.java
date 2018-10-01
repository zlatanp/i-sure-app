package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by Jasmina on 10/12/2017.
 */
@Entity
@NoArgsConstructor
@Data
public class Merchant {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Length(max = 30)
    private String merchantId;

    @Column
    @Length(max = 100)
    private String password;

    @Column
    private String name;

    @OneToOne
    private Account account;
}