package fr.formation.inti.data.kafka.message;

import java.util.Date;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class Price {
	@Id

	private Long idPrix;
	private double montant;
	private boolean active;
	private String code;
	private Date date;
}
