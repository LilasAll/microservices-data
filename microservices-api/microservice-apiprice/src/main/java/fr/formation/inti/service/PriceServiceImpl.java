package fr.formation.inti.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.inti.repository.PriceRepository;
import fr.formation.inti.repository.model.Price;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PriceServiceImpl implements IPriceService{

	@Autowired
	
	private PriceRepository priceRepository;
	
	
	@Override
	public Mono<Price> register(Price price) {
		// TODO Auto-generated method stub
		return priceRepository.save(price);
	}

	@Override
	public Flux<Price> getPrices() {
		// TODO Auto-generated method stub
		return priceRepository.findAll();
	}

	@Override
	public Mono<Price> update(Price p) {
		// TODO Auto-generated method stub
		return priceRepository.save(p);
	}

	@Override
	public Mono<Price> active(Price p) {
		p.setActive(true);
		return priceRepository.save(p);
	}

	@Override
	public Mono<Price> desactive(Price p) {
		p.setActive(false);
		return priceRepository.save(p);
	}

	@Override
	public Mono<Void> deleteById(Long idPrix) {
		// TODO Auto-generated method stub 
		return priceRepository.deleteById(idPrix);
	}

	@Override
	public Mono<Price> findById(Long idPrix) {
		// TODO Auto-generated method stub
		return priceRepository.findById(idPrix);
	}



}
