package com.ftn.service;

import com.ftn.model.database.Card;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;

import java.util.List;

/**
 * Created by Jasmina on 04/12/2017.
 */
public interface CardService {

    List<Card> read();

    Card create(Card card);

    Card update(Long id, Card card);

    void delete(Long id);

    Card findCard(PaymentOrderDTO paymentOrderDTO);
}
