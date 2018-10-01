package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Jasmina on 16/12/2017.
 */
@Entity
@NoArgsConstructor
@Data
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String url;

    @Column
    private double amount;

    @Column
    private long merchantOrderId;

    @OneToOne
    private Merchant merchant;
}
