package fr.formation.inti.data.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import fr.formation.inti.data.kafka.message.Price;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ConsumerBuilder {
	
	@Autowired
	Elasticservice elastic;
	
	@KafkaListener(topics = "${kafka.topic-name}", groupId = "${kafka.consumer-group-id}")
	public void consume(Price price) {
		log.info("Price readed " + price.toString() ); 
		elastic.enregistrer(price);
}}
