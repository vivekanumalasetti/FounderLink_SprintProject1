package com.founderlink.messagingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FounderlinkMessagingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FounderlinkMessagingServiceApplication.class, args);
	}

}
