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
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    @Column
    private double balance = 0.0;

    @Column
    private double reserved = 0.0;

    @ManyToOne
    private Bank bank;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

}
