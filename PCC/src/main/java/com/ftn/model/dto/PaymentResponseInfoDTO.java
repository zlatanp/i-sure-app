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
public class PaymentResponseInfoDTO {

    public enum CardAuthStatus{
        SUCCESSFUL,
        FAILED
    }

    public enum TransactionStatus{
        SUCCESSFUL,
        FAILED,
        CARD_AUTH_FAILURE
    }

    @NotNull
    private long acquirerOrderId;

    @NotNull
    private Date acquirerTimestamp;

    private long issuerOrderId;

    private Date issuerTimestamp;

    @NotNull
    private CardAuthStatus cardAuthStatus;

    @NotNull
    private TransactionStatus transactionStatus;
}
