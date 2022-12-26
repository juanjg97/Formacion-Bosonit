package com.bosonit.block12kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Block12KafkaApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	private static final Logger log = LoggerFactory.getLogger(Block12KafkaApplication.class);

	@KafkaListener(topics = "juan-topic",groupId = "juan-group")
	public void listen(String message){
		log.info("Mensaje recibido, el mensaje es:  "+message);

	}

	public static void main(String[] args) {
		SpringApplication.run(Block12KafkaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------------------- Mensaje desde consola ---------------------------");
		kafkaTemplate.send("juan-topic","=======> \nMensaje de prueba");
	}
}
