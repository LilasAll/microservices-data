package fr.formation.inti.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import fr.formation.inti.repository.model.Price;
import reactor.core.publisher.Flux;

@Repository
public interface PriceRepository extends ReactiveMongoRepository<Price, Long> {

	@Query("{'$and':[ {'active': true}, {'date': {$gte: ?0}} ] }")
	Flux<Price> findByDateAndActive(Date date);
	
	
}
