package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner {
	private final AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfilesApplication.class, args);
	}

	@Override
	public void run(String... args)
	{
		log.info("=> My app perfil: {}. Url: {}", appConfig.getPerfil(), appConfig.getUrl());
	}

}
