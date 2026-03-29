package com.founderlink.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FounderlinkAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FounderlinkAuthServiceApplication.class, args);
	}

}
