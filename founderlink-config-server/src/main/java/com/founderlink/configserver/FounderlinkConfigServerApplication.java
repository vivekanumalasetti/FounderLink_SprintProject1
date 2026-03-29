package com.founderlink.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FounderlinkConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FounderlinkConfigServerApplication.class, args);
	}

}
