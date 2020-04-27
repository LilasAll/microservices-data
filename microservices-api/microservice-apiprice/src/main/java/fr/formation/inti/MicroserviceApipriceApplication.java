package fr.formation.inti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;



@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan

@EnableReactiveMongoRepositories
public class MicroserviceApipriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApipriceApplication.class, args);
		
	    }
	}


