package fr.formation.inti.service;



import java.util.Date;

import fr.formation.inti.repository.model.Price;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPriceService {

	Mono<Price> register(Price price);

	public Flux<Price> getPrices();

	public Mono<Price> update(Price p);
	public Mono<Price> active(Price p);
	public Mono<Price> desactive(Price p);
	public Mono<Void> deleteById (Long idPrix);
	public Flux<Price> findByDateAndActive (Date date);

} 
