package com.ftn.model.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Jasmina on 24/06/2017.
 */
@Component
@ConfigurationProperties(prefix="environment")
public class EnvironmentProperties {

    private String acquirerUrl;

    private String issuerUrl;

    private String acquirerPort;

    private String issuerPort;

    public String getAcquirerUrl() {
        return acquirerUrl;
    }

    public void setAcquirerUrl(String acquirerUrl) {
        this.acquirerUrl = acquirerUrl;
    }

    public String getIssuerUrl() {
        return issuerUrl;
    }

    public void setIssuerUrl(String issuerUrl) {
        this.issuerUrl = issuerUrl;
    }

    public String getAcquirerPort() {
        return acquirerPort;
    }

    public void setAcquirerPort(String acquirerPort) {
        this.acquirerPort = acquirerPort;
    }

    public String getIssuerPort() {
        return issuerPort;
    }

    public void setIssuerPort(String issuerPort) {
        this.issuerPort = issuerPort;
    }
}
