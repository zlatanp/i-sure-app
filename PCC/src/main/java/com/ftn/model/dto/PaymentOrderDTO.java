package com.ftn.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Data
@NoArgsConstructor
public class PaymentOrderDTO {

    @NotNull
    private long acquirerOrderId;

    @NotNull
    private Date acquirerTimestamp;

    @NotNull
    private String PAN;

    @NotNull
    private int securityCode;

    @NotNull
    private String cardHolderName;

    @NotNull
    private int cardExpirationMonth;

    @NotNull
    private int cardExpirationYear;

    @NotNull
    private double amount;
}
