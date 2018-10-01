package com.ftn.model.dto.onlinepayment;

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


    private long acquirerOrderId;


    private Date acquirerTimestamp;

    @NotNull
    private String pan;

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
