package com.ftn.util;

import com.ftn.model.database.Bank;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Jasmina on 30/01/2018.
 */
@Component
public class PccApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String issuerUrl = environmentProperties.getIssuerUrl();
        List<Bank> bankList = bankRepository.findAll();
        for (Bank bank : bankList) {
            bank.setUrl(issuerUrl);
            bankRepository.save(bank);
        }
    }
}
