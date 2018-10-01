package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Card;
import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.repository.TransactionRepository;
import com.ftn.service.AcquirerService;
import com.ftn.service.CardService;
import com.ftn.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jasmina on 16/12/2017.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private AcquirerService acquirerService;

    @Override
    public List<Transaction> read() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction create(PaymentOrderDTO paymentOrderDTO, Transaction.TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setStatus(Transaction.Status.PENDING);
        transaction.setType(type);  // in Acquirer bank this is INCOME in Issuer this is CHARGE
        transaction.setAmount(paymentOrderDTO.getAmount());

        if(type.equals(Transaction.TransactionType.CHARGE)){
            Card card = cardService.findCard(paymentOrderDTO);
            transaction.setAccount(card.getAccount());
            transaction.setType(Transaction.TransactionType.CHARGE);
            transaction.setStatus(Transaction.Status.BOOKED);
        }
        transaction =  transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        Transaction existing = transactionRepository.findById(id);
        if(existing != null){
            existing.setTimestamp(transaction.getTimestamp());
            existing.setType(transaction.getType());
            existing.setStatus(transaction.getStatus());
            existing.setAmount(transaction.getAmount());
            existing.setAccount(transaction.getAccount());
            existing.setPayment(transaction.getPayment());
            existing = transactionRepository.save(existing);
        }else{
            existing = null;
        }
        return existing;
    }

    @Override
    public void delete(Long id) {
        transactionRepository.delete(id);
    }

    @Override
    public Transaction findById(Long id) {
        Transaction transaction = transactionRepository.findById(id);
        return transaction;
    }
}
