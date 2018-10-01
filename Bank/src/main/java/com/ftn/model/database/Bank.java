package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zlatan on 6/24/17.
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

    @Column(length = 8)
    private String SWIFTcode;

    @Column(name = "bank_account", length = 20)
    private String accountNumber;

    @Column
    private int bankCode; //bankCode banke prva tri broja

    @OneToMany(mappedBy = "bank")
    private List<Account> accounts;
}
