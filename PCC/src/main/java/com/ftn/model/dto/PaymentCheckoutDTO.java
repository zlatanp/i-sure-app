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
public class PaymentCheckoutDTO {

    @NotNull
    private long merchantOrderId;

    @NotNull
    private long acquirerOrderId;

    @NotNull
    private Date acquirerTimestamp;

    @NotNull
    private long paymentId;

    private String successUrl;

    private String errorUrl;
}
