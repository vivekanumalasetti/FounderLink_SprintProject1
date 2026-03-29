package com.founderlink.investmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FounderlinkInvestmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FounderlinkInvestmentServiceApplication.class, args);
	}

}
