package com.ftn.model.dto.transaction;

import com.ftn.model.database.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Jasmina on 16/12/2017.
 */
@Data
@NoArgsConstructor
public class TransactionDTO {

    private Long id;

    private Date timestamp;

    @NotNull
    private double amount;

    @NotNull
    private String type;

    @NotNull
    private String status;

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.timestamp = transaction.getTimestamp();
        this.amount = transaction.getAmount();
        this.type = transaction.getType().toString();
        this.status = transaction.getType().toString();
    }
}
