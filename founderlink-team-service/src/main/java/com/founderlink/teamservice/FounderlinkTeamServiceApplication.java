package com.founderlink.teamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FounderlinkTeamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FounderlinkTeamServiceApplication.class, args);
	}

}
