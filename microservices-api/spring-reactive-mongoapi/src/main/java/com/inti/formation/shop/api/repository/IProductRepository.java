package com.inti.formation.shop.api.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


import com.inti.formation.shop.api.repository.model.Product;

import reactor.core.publisher.Flux;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, Long> {
//
//	Flux<Product> findByLabel(String label);	
//	
//	@Query(value="{'origine': {$eq: 'France'}}")
//    Flux<Product> findByOrigineFrance(final String origine);
//
//	
	
	/////////TEST///
}
