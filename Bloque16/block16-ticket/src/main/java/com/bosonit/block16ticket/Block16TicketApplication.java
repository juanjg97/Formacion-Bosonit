package com.bosonit.block16ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Block16TicketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Block16TicketApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//createTickets.createData();
	}
}
