package fr.formation.inti.data.kafka.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import fr.formation.inti.data.kafka.message.Price;

public interface PriceRepository extends ElasticsearchCrudRepository<Price, Long>{


}
