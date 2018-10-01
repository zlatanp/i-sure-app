package com.ftn.controller.dummy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ftn.model.dto.PaymentInquiryDTO;
import com.ftn.model.dto.PaymentInquiryInfoDTO;
import com.ftn.service.PaymentInquiryService;
import com.ftn.service.PaymentService;
import com.ftn.service.TransactionService;
import com.ftn.service.implementation.TransactionServiceImpl;

@Controller
@RequestMapping("/dummyinquiries")
public class PayPalDummy {
	@Value("${pc.home}")
    private String pc_home;
	
	@Value("${pc.payment.inquiries}")
	private String pc_inquiries;
	
	public static final String PAYPAL_SUCCESS_URL = "dummyinquiries/testni";
	public static final String PAYPAL_CANCEL_URL = "dummyinquiries/cancel";
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	private final TransactionService transactionService;
	private final PaymentService paymentService;
	private final PaymentInquiryService paymentInquiryService;

    @Autowired
    public PayPalDummy(TransactionServiceImpl transactionService,
    		PaymentInquiryService paymentInquiryService,
    		PaymentService paymentService){
        this.transactionService = transactionService;
        this.paymentInquiryService = paymentInquiryService;
        this.paymentService = paymentService;
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "index";
	}
    
    @PostMapping
    public String sendPaymentInquiry(ModelMap model) {
       
        PaymentInquiryDTO piDTO = new PaymentInquiryDTO();
        piDTO.setAmount(1);
        piDTO.setPaymentType("paypal");
        
        ResponseEntity<PaymentInquiryInfoDTO> response = restTemplate.postForEntity(pc_home + pc_inquiries, new HttpEntity<>(piDTO),
                PaymentInquiryInfoDTO.class);
        
        System.out.println("=========================DummyController============================");
       System.out.println("Dobijen odgovor");
       System.out.println(response.getBody().getPaymentUrl());
       
       model.addAttribute("url", response.getBody().getPaymentUrl());
       
        return  response.getBody().getPaymentUrl();
    }
    
    
    
}