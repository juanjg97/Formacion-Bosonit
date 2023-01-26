package com.bosonit.tripbackend;

import com.bosonit.tripbackend.initial.data.DataClientesAndViajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class TripBackendApplication implements CommandLineRunner {
	@Autowired
	DataClientesAndViajes dataClientesAndViajes;
	public static void main(String[] args) {
		SpringApplication.run(TripBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataClientesAndViajes.createData();
	}
}
