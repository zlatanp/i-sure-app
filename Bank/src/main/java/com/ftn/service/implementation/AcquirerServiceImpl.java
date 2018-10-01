package com.ftn.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.model.database.Account;
import com.ftn.model.database.Merchant;
import com.ftn.model.database.Payment;
import com.ftn.model.database.Transaction;
import com.ftn.model.dto.onlinepayment.PaymentCheckoutDTO;
import com.ftn.model.dto.onlinepayment.PaymentInquiryDTO;
import com.ftn.model.dto.onlinepayment.PaymentInquiryInfoDTO;
import com.ftn.model.dto.onlinepayment.PaymentOrderDTO;
import com.ftn.model.dto.onlinepayment.PaymentResponseInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.MerchantRepository;
import com.ftn.service.AcquirerService;
import com.ftn.service.OnlinePaymentService;
import com.ftn.service.TransactionService;

/**
 * Created by Jasmina on 04/12/2017.
 */
@Service
public class AcquirerServiceImpl implements AcquirerService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OnlinePaymentService onlinePaymentService;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Override
    public boolean checkInquiry(PaymentInquiryDTO paymentInquiryDTO) {
        boolean found;
        String merchantId = paymentInquiryDTO.getMerchantId();
        String merchantPassword = paymentInquiryDTO.getMerchantPassword();
        Merchant merchant = merchantRepository.findByMerchantIdAndPassword(merchantId, merchantPassword);
        
        Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
        if (merchant != null) {
            found = true;
            logger.info("Merchant found");
        } else {
            found = false;
            logger.info("Merchant not found");
        }
        return found;
    }

    @Override
    public PaymentInquiryInfoDTO generateInquiryInfo(PaymentInquiryDTO paymentInquiryDTO) {
    	Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
        Payment payment = onlinePaymentService.create(paymentInquiryDTO);

        logger.info("Payment created");

        PaymentInquiryInfoDTO paymentInquiryInfoDTO = new PaymentInquiryInfoDTO();
        paymentInquiryInfoDTO.setPaymentUrl(payment.getUrl());
        paymentInquiryInfoDTO.setPaymentId(payment.getId().toString());
        return paymentInquiryInfoDTO;
    }

    @Override
    public PaymentOrderDTO generateOrder(PaymentOrderDTO paymentOrderDTO, long paymentId) {
        Transaction transaction = transactionService.create(paymentOrderDTO, Transaction.TransactionType.INCOME);
        Payment payment = onlinePaymentService.findByPaymentId(paymentId);
        if (payment != null) {
            Account account = payment.getMerchant().getAccount();
            transaction.setAccount(account);
            transaction.setPayment(payment);
            transaction.setType(Transaction.TransactionType.INCOME);
            transaction = transactionService.update(transaction.getId(), transaction);
            
            Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
            logger.info("Income transaction created");
        }
        paymentOrderDTO.setAcquirerOrderId(transaction.getId());
        paymentOrderDTO.setAcquirerTimestamp(transaction.getTimestamp());
        return paymentOrderDTO;
    }

    @Override
    public PaymentCheckoutDTO generateCheckout(PaymentResponseInfoDTO paymentResponseInfoDTO) {
        PaymentCheckoutDTO paymentCheckout = new PaymentCheckoutDTO();
        long acquirerOrderId = paymentResponseInfoDTO.getAcquirerOrderId();
        paymentCheckout.setAcquirerOrderId(acquirerOrderId);
        paymentCheckout.setAcquirerTimestamp(paymentResponseInfoDTO.getAcquirerTimestamp());

        String selfUrl = environmentProperties.getSelfUrl();
        String errorUrl = selfUrl + "#/acquirer/error";
        String successUrl = selfUrl + "#/acquirer/success";

        Transaction transaction = transactionService.findById(acquirerOrderId);
        if (transaction != null) {
            String paymentId = transaction.getPayment().getId().toString();
            paymentCheckout.setPaymentId(paymentId);
            long merchantOrderId = transaction.getPayment().getMerchantOrderId();
            paymentCheckout.setMerchantOrderId(merchantOrderId);
            paymentCheckout.setSuccessUrl(successUrl);

            //Change status and account balance
            if (paymentResponseInfoDTO.getCardAuthStatus().equals(PaymentResponseInfoDTO.CardAuthStatus.SUCCESSFUL)
                    && paymentResponseInfoDTO.getTransactionStatus().equals(PaymentResponseInfoDTO.TransactionStatus.SUCCESSFUL)) {
                transaction.setStatus(Transaction.Status.BOOKED);
                paymentCheckout.setSuccessUrl(successUrl);
                double amount = transaction.getPayment().getAmount();
                Account merchantAccount = transaction.getAccount();
                merchantAccount.setBalance(merchantAccount.getBalance() + amount);
                transaction.setAccount(merchantAccount);
                transactionService.update(transaction.getId(), transaction);

                Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
                logger.info("Successful transaction");
            } else {
                transaction.setStatus(Transaction.Status.REVERSED);
                paymentCheckout.setErrorUrl(errorUrl);
                transactionService.update(transaction.getId(), transaction);

                Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
                logger.info("Reversed transaction");
            }
        } else {
            paymentCheckout.setPaymentId("0");
            paymentCheckout.setMerchantOrderId(0);
            paymentCheckout.setErrorUrl(errorUrl);

            Logger logger = LoggerFactory.getLogger(AcquirerServiceImpl.class);
            logger.info("Transaction not found");
        }
        return paymentCheckout;
    }

    @Override
    public String getBankName() {
        String bankName = environmentProperties.getBankName();
        return bankName;
    }

    @Override
    public double getAmountForPaymentId(long paymentId) {
        double amount = 0.0;
        Payment payment = onlinePaymentService.findByPaymentId(paymentId);
        if(payment != null){
            amount = payment.getAmount();
        }
        return amount;
    }
}
