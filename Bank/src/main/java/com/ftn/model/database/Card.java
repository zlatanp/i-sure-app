package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Entity
@NoArgsConstructor
@Data
public class Card {

    enum CardType{
        VISA,
        DINA,
        MASTER_CARD,
        AMERICAN_EXPRESS
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String pan;

    @Column(nullable = false)
    private int securityCode;

    @Column(nullable = false)
    private int expirationMonth;

    @Column(nullable = false)
    private int expirationYear;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false)
    private int pin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @ManyToOne
    private Account account;
}
