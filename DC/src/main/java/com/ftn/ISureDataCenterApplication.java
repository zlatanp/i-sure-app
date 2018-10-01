package com.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ftn.intercepting.AuthorizationInterceptor;

@SpringBootApplication
@Configuration
public class ISureDataCenterApplication extends WebMvcConfigurerAdapter {

	@Bean
	public AuthorizationInterceptor authorizationInterceptor() {
	    return new AuthorizationInterceptor();
	}
	
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor());
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ISureDataCenterApplication.class, args);
	}
}
