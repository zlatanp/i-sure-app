package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Merchant;
import com.ftn.model.database.Payment;
import com.ftn.model.dto.onlinepayment.PaymentInquiryDTO;
import com.ftn.model.dto.onlinepayment.PaymentInquiryInfoDTO;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.MerchantRepository;
import com.ftn.repository.PaymentRepository;
import com.ftn.service.OnlinePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jasmina on 16/12/2017.
 */
@Service
public class OnlinePaymentServiceImpl implements OnlinePaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Override
    public Payment findByPaymentId(long id) {
        Payment payment;
        try {
            payment = paymentRepository.findById(id).orElseThrow(NotFoundException::new);
        }catch (NotFoundException exception){
            payment = null;
        }
        return payment;
    }

    @Override
    public Payment create(PaymentInquiryDTO paymentInquiryDTO) {
        String merchantId = paymentInquiryDTO.getMerchantId();
        String merchantPassword = paymentInquiryDTO.getMerchantPassword();
        Payment payment = new Payment();
        payment = paymentRepository.save(payment);
        long paymentId = payment.getId();
        payment.setUrl(generatePaymentUrl(paymentId));
        payment.setMerchantOrderId(paymentInquiryDTO.getMerchantOrderId());
        payment.setAmount(paymentInquiryDTO.getAmount());
        Merchant merchant = merchantRepository.findByMerchantIdAndPassword(merchantId, merchantPassword);
        if(merchant != null){
            payment.setMerchant(merchant);
        }
        return paymentRepository.save(payment);
    }

    private String generatePaymentUrl(Long paymentId){
        String paymentUrl = environmentProperties.getSelfUrl();
        paymentUrl += "#/acquirer/order/" + paymentId;
        return paymentUrl;
    }
}
