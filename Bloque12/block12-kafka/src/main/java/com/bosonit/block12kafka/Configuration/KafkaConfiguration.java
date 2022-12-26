package com.bosonit.block12kafka.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    //Configuración para consumer-----------------------------------------------
    @Bean
    public Map<String, Object> consumerProps() {
        Map<String, Object>props=new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "juan-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<String,String>(consumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> listenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(consumerFactory());
        return listenerContainerFactory;
    }

    //Configuración para producer-----------------------------------------------
    @Bean
    public  Map<String, Object> producerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        DefaultKafkaProducerFactory<String,String> defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerProps());

        KafkaTemplate <String,String> template = new KafkaTemplate<>(defaultKafkaProducerFactory);

        return template;
    }
    //Configuración para crear un topic automáticamente por ser un bean-----------------------------------------------
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("juan-topic").build();
    }

}
