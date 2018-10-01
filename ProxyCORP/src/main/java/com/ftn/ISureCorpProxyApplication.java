package com.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.ftn.filters.SimpleFilter;

@EnableZuulProxy
@SpringBootApplication
public class ISureCorpProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ISureCorpProxyApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}
