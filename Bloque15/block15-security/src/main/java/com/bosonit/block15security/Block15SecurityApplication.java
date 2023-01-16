package com.bosonit.block15security;

import com.bosonit.block15security.domain.entities.User;
import com.bosonit.block15security.repositories.UserRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Block15SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block15SecurityApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void createUser(){
		User user1 = new User();
		user1.setName("Juan");
		user1.setUsername("juanjg");
		user1.setEmail("juan@mail.com");
		user1.setPassword(new BCryptPasswordEncoder().encode("abcd"));


		userRepository.save(user1);

		User user2 = new User();
		user2.setName("Carlos");
		user2.setUsername("carlosjg");
		user2.setEmail("carlos@mail.com");
		user2.setPassword(new BCryptPasswordEncoder().encode("1234"));

		userRepository.save(user2);

		User user3 = new User();
		user3.setName("Diana");
		user3.setUsername("dianags");
		user3.setEmail("diana@mail.com");
		user3.setPassword(new BCryptPasswordEncoder().encode("qwerty"));

		userRepository.save(user3);
	}
}
