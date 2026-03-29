package com.founderlink.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FounderlinkEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FounderlinkEurekaServerApplication.class, args);
	}

}
