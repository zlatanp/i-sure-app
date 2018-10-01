package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jasmina on 16/12/2017.
 */
@Entity
@NoArgsConstructor
@Data
public class Transaction {

    public enum TransactionType {
        INCOME,
        CHARGE
    }

    public enum Status {
        PENDING,
        BOOKED,
        REVERSED
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date timestamp;

    @Column
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @ManyToOne
    private Account account;

    @OneToOne
    private Payment payment;

    @PrePersist
    protected void onCreate() {
        timestamp = new Date();
    }
}
