package com.ftn.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ftn.model.dto.PaymentCheckoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.paypal.config.PaypalPaymentIntent;
import com.ftn.paypal.config.PaypalPaymentMethod;
import com.ftn.paypal.util.URLUtils;
import com.ftn.service.PayPalService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PayPalServiceImpl implements PayPalService {

    public static final String PAYPAL_SUCCESS_URL = "inquiries/testni";
    public static final String PAYPAL_CANCEL_URL = "inquiries/cancel";

    @Value("${ph.home}")
    private String ph_home;

    @Value("${ph.payment.checkout}")
    private String ph_checkout;
    
    @Value("${ph.self.ip}")
    private String ph_self_ip;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private APIContext apiContext;

    @Override
    public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO, HttpServletRequest request) {

        String cancelUrl = URLUtils.getBaseURl(request, ph_self_ip) + "/" + PAYPAL_CANCEL_URL;
        String successUrl = URLUtils.getBaseURl(request, ph_self_ip) + "/" + PAYPAL_SUCCESS_URL;

        System.out.println("Success url " + successUrl);

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", piDTO.getAmount()));

        Transaction transaction = new Transaction();
        transaction.setDescription("Paypal payment description");
        transaction.setAmount(amount);

        Payee payee = new Payee();
        payee.setEmail("teauvranju-facilitator@hotmail.com");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(PaypalPaymentMethod.paypal.toString());

        Payment payment = new Payment();
        payment.setIntent(PaypalPaymentIntent.sale.toString());
        payment.setPayer(payer);

        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);

        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        Payment p;
        try {
            p = payment.create(apiContext);
        } catch (PayPalRESTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            p = null;
            return null;
        }

        List<Links> lista = p.getLinks();

        System.out.println("ID " + p.getId());

        String approval_url = null;

        for (Links links : lista) {
            if (links.getRel().equals("approval_url")) {
                // approval_url = "redirect:" + links.getHref();
                approval_url = links.getHref();
                break;
            }
        }

        String[] parts = approval_url.split("token=");
        String token = parts[1];

        // privremeno
        PaymentInquiryInfoDTO piInfoDTO = new PaymentInquiryInfoDTO();
        // piInfoDTO.setPaymentId(p.getId());
        piInfoDTO.setPaymentId(token);
        piInfoDTO.setPaymentUrl(approval_url);

        return piInfoDTO;
    }

    @Override
    public PaymentInquiryInfoDTO sendPaymentInquiry(PaymentInquiryDTO piDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    @Override
    public void successPayment(String token) {
        PaymentCheckoutDTO paymentCheckoutDTO = new PaymentCheckoutDTO();
        paymentCheckoutDTO.setPaymentId(token);
        ResponseEntity response = restTemplate.postForEntity(ph_home + ph_checkout + "/success",
                new HttpEntity<>(paymentCheckoutDTO), PaymentCheckoutDTO.class);
    }

    @Override
    public void cancelPayment(String token) {
        PaymentCheckoutDTO paymentCheckoutDTO = new PaymentCheckoutDTO();
        paymentCheckoutDTO.setPaymentId(token);
        ResponseEntity response = restTemplate.postForEntity(ph_home + ph_checkout + "/cancel",
                new HttpEntity<>(paymentCheckoutDTO), PaymentCheckoutDTO.class);
    }

}
