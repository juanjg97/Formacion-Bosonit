package com.bosonit.block12kafka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mensaje")
public class ProducerController {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void enviarMensaje(@RequestBody String mensaje) {
        kafkaTemplate.send("juan-topic", mensaje);
    }
}
