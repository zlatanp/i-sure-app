package com.ftn.service.implementation;

import com.ftn.exception.NotFoundException;
import com.ftn.model.database.Bank;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.repository.BankRepository;
import com.ftn.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Jasmina on 10/12/2017.
 */
@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Override
    public String getIssuerUrl(String BIN) {
        String bankUrl = "";
        try {
            Bank bank = bankRepository.findBankByCode(BIN).orElseThrow(NotFoundException::new);
            bankUrl = bank.getUrl() + "issuer";
        }catch (NotFoundException exception){
            bankUrl = "";
        }
        return bankUrl;
    }
}
