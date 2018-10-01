package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Account;
import com.ftn.model.database.Card;
import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.AccountRepository;
import com.ftn.repository.CardRepository;
import com.ftn.service.IssuerService;
import com.ftn.service.OnlinePaymentService;
import com.ftn.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Service
public class IssuerServiceImpl implements IssuerService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OnlinePaymentService onlinePaymentService;

    @Autowired
    private EnvironmentProperties environmentProperties;


    @Override
    public PaymentResponseInfoDTO.CardAuthStatus cardAuthentication(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO.CardAuthStatus cardAuthStatus;
        String pan = paymentOrderDTO.getPan();
        int securityCode = paymentOrderDTO.getSecurityCode();
        int month = paymentOrderDTO.getCardExpirationMonth();
        int year = paymentOrderDTO.getCardExpirationYear();

        Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);

        try {
            Card card = cardRepository.findByPan(pan).orElseThrow(NotFoundException::new);
            if (card.getSecurityCode() == securityCode && card.getExpirationMonth() == month
                    && card.getExpirationYear() == year) {
                cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.SUCCESSFUL;
                logger.info("Card authentication ok");
            } else {
                cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.FAILED;
                logger.error("Card authentication error");
            }
        } catch (NotFoundException exception) {
            cardAuthStatus = PaymentResponseInfoDTO.CardAuthStatus.FAILED;
            logger.error("Card authentication error");
        }
        return cardAuthStatus;
    }

    @Override
    public PaymentResponseInfoDTO.TransactionStatus transactionAuthorization(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO.TransactionStatus transactionStatus;
        String pan = paymentOrderDTO.getPan();
        Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
        try {
            Card card = cardRepository.findByPan(pan).orElseThrow(NotFoundException::new);
            Account account = card.getAccount();
            if (paymentOrderDTO.getAmount() > account.getBalance()) {
                transactionStatus = PaymentResponseInfoDTO.TransactionStatus.FAILED;
                logger.error("Transaction authorization error");
            } else {
                transactionStatus = PaymentResponseInfoDTO.TransactionStatus.SUCCESSFUL;
                logger.info("Transaction authorization ok");
            }
        } catch (NotFoundException exception) {
            transactionStatus = PaymentResponseInfoDTO.TransactionStatus.CARD_AUTH_FAILURE;
            logger.error("Card authentication error");
        }
        return transactionStatus;
    }

    @Override
    public Transaction transfer(PaymentOrderDTO paymentOrderDTO) {
        Transaction transaction;
        String PAN = paymentOrderDTO.getPan();
        double orderAmount = paymentOrderDTO.getAmount();
        Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
        Card card = cardRepository.findByPan(PAN).orElseThrow(NotFoundException::new);
        try {
            Account account = card.getAccount();
            double balance = account.getBalance();
            balance -= orderAmount;
            account.setBalance(balance);
            accountRepository.save(account);
            transaction = transactionService.create(paymentOrderDTO, Transaction.TransactionType.CHARGE);
            logger.info("Successful founds transfer");
        } catch (NotFoundException exception) {
            transaction = null;
            logger.error("Fail founds transfer");
        }
        return transaction;
    }

    @Override
    public PaymentResponseInfoDTO generateResponse(PaymentOrderDTO paymentOrderDTO) {
        PaymentResponseInfoDTO paymentResponseInfoDTO = new PaymentResponseInfoDTO();
        PaymentResponseInfoDTO.CardAuthStatus cardAuthStatus = cardAuthentication(paymentOrderDTO);
        PaymentResponseInfoDTO.TransactionStatus transactionStatus = transactionAuthorization(paymentOrderDTO);
        Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);

        if (cardAuthStatus.equals(PaymentResponseInfoDTO.CardAuthStatus.SUCCESSFUL)
                && transactionStatus.equals(PaymentResponseInfoDTO.TransactionStatus.SUCCESSFUL)) {
            Transaction transaction = transfer(paymentOrderDTO);
            if (transaction != null) {
                paymentResponseInfoDTO.setIssuerOrderId(transaction.getId());
                paymentResponseInfoDTO.setIssuerTimestamp(transaction.getTimestamp());
            }
        }
        paymentResponseInfoDTO.setAcquirerOrderId(paymentOrderDTO.getAcquirerOrderId());
        paymentResponseInfoDTO.setAcquirerTimestamp(paymentOrderDTO.getAcquirerTimestamp());
        paymentResponseInfoDTO.setCardAuthStatus(cardAuthStatus);
        paymentResponseInfoDTO.setTransactionStatus(transactionStatus);

        logger.info("Issuer response generated");

        return paymentResponseInfoDTO;
    }
}
