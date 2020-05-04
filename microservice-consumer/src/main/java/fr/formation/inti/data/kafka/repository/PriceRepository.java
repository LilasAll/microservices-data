package fr.formation.inti.data.kafka.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import fr.formation.inti.data.kafka.model.Price;

public interface PriceRepository extends ElasticsearchCrudRepository<Price, Long> {

}
