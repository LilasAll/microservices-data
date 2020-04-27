package fr.formation.inti;

import java.awt.Event;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import fr.formation.inti.repository.model.Price;
import fr.formation.inti.serde.JsonPOJOSerializer;




@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableScheduling
@EnableReactiveMongoRepositories
public class MicroserviceApipriceApplication {
	 @Value("${kafka.bootstrap-server}")
	    private String kafkaBrokerUrl;

	    @Value("${kafka.acks}")
	    private String acks;

	    @Value("${kafka.retries}")
	    private String retries;

	    @Value("${kafka.buffer-memory}")
	    private String bufferMemory;

	    @Value("${kafka.batch-size}")
	    private String batchSize;

	    @Value("${kafka.client-id}")
	    private String clientId;

	    @Value("${kafka.compression-type}")
	    private String compressionType;

	    @Value("${kafka.user}")
	    private String user;

	    @Value("${kafka.password}")
	    private String password;

	    private static final String SECURITY_PROTOCOL = "security.protocol";
	    private static final String SASL_MECHANISM = "sasl.mechanism";
	    private static final String SASL_JAAS_CONFIG = "sasl.jaas.config";

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApipriceApplication.class, args);
		
	    }
	@Bean
    public Map<String, Object> producerConfigs() {
	Map<String, Object> conf = new HashMap<>();
	// Affectation des variables du fichier application.yml au produceur)
	conf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerUrl);
	// type de serialize de clé
	conf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	// type de serialize de clé (la clé est un string et la valeur est un json)
	conf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonPOJOSerializer.class.getName());
	// If the request fails, the producer can automatically retry, nombre de tentative d'essai d'insertion de la donnée avant d'abandonner
	conf.put(ProducerConfig.RETRIES_CONFIG, retries);
	// l'acquittement, nombre de replication de la donnée sur les brocker en respectant le nombre de min.insync.replicas
	conf.put(ProducerConfig.ACKS_CONFIG, acks);
	conf.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
	conf.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
	// identifiant du produceur
	conf.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
	conf.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType); // lz4 compression mode unused cpu
	// Authentification
	//conf.put(SECURITY_PROTOCOL, "SASL_PLAINTEXT");
	//conf.put(SASL_MECHANISM, "SCRAM-SHA-512");
	//conf.put(SASL_JAAS_CONFIG, "org.apache.kafka.common.security.scram.ScramLoginModule required username=" + user
	//	+ " password=" + password + ";");
	conf.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1);
//	conf.put(StreamsConfig.EXACTLY_ONCE, true); // controls that the message is send one time when ack is all
	return conf;
    }

    @Bean
    // <type de clé (string), le type de donnée (event)>
    public ProducerFactory<String, Price> producerFactory() {
	return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Price> kafkaTemplate() {
	return new KafkaTemplate<>(producerFactory());
    }


	}


