package com.ftn.controller;

import com.ftn.model.dto.*;
import com.ftn.service.EmailService;
import com.ftn.service.ReportService;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.ftn.service.PaymentService;
import com.ftn.service.TransactionService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/checkout")
public class PaymentCheckoutController {

    private RestTemplate restTemplate = new RestTemplate();

    private final TransactionService transactionService;

    private final PaymentService paymentService;

    private final ReportService reportService;

    private final EmailService emailService;

    Logger logger ;

    @Autowired
    public PaymentCheckoutController(TransactionService transactionService, PaymentService paymentService, ReportService reportService, EmailService emailService) {
        this.transactionService = transactionService;
        this.paymentService = paymentService;
        this.reportService = reportService;
        this.emailService = emailService;
        this.logger = LoggerFactory.getLogger(PaymentCheckoutController.class);
    }

    @PostMapping(value = "success")
    public ResponseEntity successPay(@RequestBody PaymentCheckoutDTO paymentCheckoutDTO) throws IOException, MessagingException {
        String paymentId = paymentCheckoutDTO.getPaymentId();

        TransactionDTO transactionDTO = transactionService.findByPaymentId(paymentId);
        transactionDTO.setStatus(TransactionStatus.BOOKED);
        if (paymentCheckoutDTO.getAcquirerOrderId() != 0 && paymentCheckoutDTO.getAcquirerTimestamp() != null) {
            transactionDTO.setAcquirerOrderId(paymentCheckoutDTO.getAcquirerOrderId());
            transactionDTO.setAcquirerTimestamp(paymentCheckoutDTO.getAcquirerTimestamp());
        }
        transactionService.update(transactionDTO.getId(), transactionDTO);

        logger.info("Checkout success");

        //Email and pdf part
        InsurancePolicyDTO insurancePolicyDTO = transactionDTO.getInsurancePolicy();
        String policyName = reportService.generatePolicyReport(insurancePolicyDTO);
        CustomerDTO carrier = insurancePolicyDTO.getCustomers().stream().filter(customerDTO -> customerDTO.isCarrier()).findFirst().orElse(null);
        String carrierMail = carrier.getEmail();
        emailService.sendEmail(carrierMail, policyName);
        return new ResponseEntity(paymentCheckoutDTO, HttpStatus.OK);
    }

    @PostMapping(value = "cancel")
    public ResponseEntity cancelPay(@RequestBody PaymentCheckoutDTO paymentCheckoutDTO) {
        String paymentId = paymentCheckoutDTO.getPaymentId();
        TransactionDTO transactionDTO = transactionService.findByPaymentId(paymentId);
        transactionDTO.setStatus(TransactionStatus.REVERSED);
        transactionService.update(transactionDTO.getId(), transactionDTO);

        logger.info("Checkout cancel");

        return new ResponseEntity(paymentCheckoutDTO, HttpStatus.NO_CONTENT);
    }
}
