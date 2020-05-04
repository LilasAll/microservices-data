package fr.formation.inti.elasticsearch.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName= "price", type = "_doc")
public class Price {
	@Id
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
