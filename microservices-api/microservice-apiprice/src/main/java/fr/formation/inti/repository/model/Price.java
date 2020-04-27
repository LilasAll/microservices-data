package fr.formation.inti.repository.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Data
@Document(collection = "price")
//@CompoundIndexes({
//        @CompoundIndex(name = "customer", def = "{ name: 1, dateNaissance: 1 }", unique = false)
//        // unique = false acceptation des doublons true non
//        // unique = true rejet  des doublons
//})
public class Price implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long idPrix;
	private double montant;
	private boolean active;
	private String code;
	private Date date;
}
