package com.ftn.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Data
@NoArgsConstructor
public class PaymentInquiryInfoDTO {

    @NotNull
    private String paymentId;

    @NotNull
    private String paymentUrl;
}
