package com.ftn.model.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Jasmina on 24/06/2017.
 */
@Component
@ConfigurationProperties(prefix = "environment")
public class EnvironmentProperties {

    private String swiftCode;

    private String nbsUrl;

    private String selfUrl;

    private String pccUrl;

    private String concentratorUrl;

    private String bankName;


    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public String getNbsUrl() {
        return nbsUrl;
    }

    public void setNbsUrl(String nbsUrl) {
        this.nbsUrl = nbsUrl;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public String getPccUrl() {
        return pccUrl;
    }

    public void setPccUrl(String pccUrl) {
        this.pccUrl = pccUrl;
    }

    public String getConcentratorUrl() {
        return concentratorUrl;
    }

    public void setConcentratorUrl(String concentratorUrl) {
        this.concentratorUrl = concentratorUrl;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
