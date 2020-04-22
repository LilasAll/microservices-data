package fr.formation.inti.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;



import fr.formation.inti.repository.model.Price;
import reactor.core.publisher.Flux;

public interface PriceRepository extends ReactiveMongoRepository<Price, Long> {

	
	Flux<Price> findByDateAndActive(Date date, boolean active);
	
	
}
