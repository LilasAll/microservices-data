package fr.formation.inti.data.kafka.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import fr.formation.inti.elasticsearch.model.Price;


@Repository
public interface PriceRepository extends ElasticsearchCrudRepository<Price, Long>{


}
