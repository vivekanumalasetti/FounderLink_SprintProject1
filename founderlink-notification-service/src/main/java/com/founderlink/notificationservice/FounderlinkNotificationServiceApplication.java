package com.founderlink.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FounderlinkNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FounderlinkNotificationServiceApplication.class, args);
	}

}
