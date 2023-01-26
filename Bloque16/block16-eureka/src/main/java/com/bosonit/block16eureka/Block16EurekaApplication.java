package com.bosonit.block16eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Block16EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16EurekaApplication.class, args);
	}

}
