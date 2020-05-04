package fr.formation.inti.data.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import fr.formation.inti.data.kafka.repository.PriceRepository;
import fr.formation.inti.elasticsearch.model.Price;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Elasticservice {


@Autowired
PriceRepository pricerepository;

public void enregistrer (Price price) {
	pricerepository.save(price);
}
}
