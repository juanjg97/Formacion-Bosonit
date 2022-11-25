package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block5PropertiesApplication implements CommandLineRunner {

	@Value("${greeting}")
	private String saludo;

	@Value("${my.number}")
	private String numero;

	@Value("${new.property:new.property no tiene valor}")
	private String npropiedad;

	public static void main(String[] args) {

		SpringApplication.run(Block5PropertiesApplication.class, args);

	}

	@Override
	public void run(String... args){
		System.out.println("El valor de greeting es: "+saludo);
		System.out.println("El valor de my.number es: "+numero);
		System.out.println("El valor de new property es: "+npropiedad);

	}

}
