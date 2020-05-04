package fr.formation.inti.data.kafka.message;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
public class Price {
	@Id
	@Field(type = FieldType.Long)
	private Long idPrix;
	@Field(type = FieldType.Double)
	private double montant;
	@Field(type = FieldType.Boolean)
	private boolean active;
	@Field(type = FieldType.Keyword)
	private String code;
	@Field(type = FieldType.Date)
	private Date date;
}
