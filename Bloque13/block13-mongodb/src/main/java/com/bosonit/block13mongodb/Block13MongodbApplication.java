package com.bosonit.block13mongodb;

import com.bosonit.block13mongodb.domain.entities.Person;
import com.bosonit.block13mongodb.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Block13MongodbApplication implements CommandLineRunner {
	@Autowired
	PersonRepository personRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Block13MongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection(Person.class);
					Person juan = new Person();
					juan.setName("Juan");
					juan.setSurname("Jasso");
					juan.setUser("juanjg");
					juan.setPassword("12345");
					juan.setCompany_email("juan@mail.com");
					juan.setPersonal_email("juan@bosonit.com");
					personRepository.save(juan);
	}
}

//Método para agregar una persona con ifPresentOrElse
/*
* 		personRepository.findPersonByUser("juanjg").ifPresentOrElse(
				person -> {
					personRepository.deletePersonByUser("juanjg");
					Person juan = new Person();
					juan.setName("Juan");
					juan.setSurname("Jasso");
					juan.setUser("juanjg");
					juan.setPassword("12345");
					juan.setCompany_email("juan@mail.com");
					juan.setPersonal_email("juan@bosonit.com");
					personRepository.save(juan);
				},
                () -> {
					Person juan = new Person();
					juan.setName("Juan");
					juan.setSurname("Jasso");
					juan.setUser("juanjg");
					juan.setPassword("12345");
					juan.setCompany_email("juan@mail.com");
					juan.setPersonal_email("juan@bosonit.com");
					personRepository.save(juan);
				}
		);*/