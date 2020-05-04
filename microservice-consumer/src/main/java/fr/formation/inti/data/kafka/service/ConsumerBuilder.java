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
		fr.formation.inti.elasticsearch.model.Price p = new fr.formation.inti.elasticsearch.model.Price();
		
		p.setIdPrix(price.getIdPrix());
		p.setMontant(price.getMontant());
//		p.setDate(price.getDate());
		p.setActive(false);
		p.setCode(price.getCode());
		
		elastic.enregistrer(p);
}}
