package com.bosonit.block16gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Block16GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16GatewayApplication.class, args);
	}

}
