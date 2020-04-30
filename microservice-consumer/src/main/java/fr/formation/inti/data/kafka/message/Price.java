package fr.formation.inti.data.kafka.message;

import lombok.Data;

@Data
public class Price {
	private String code;
    private String context;
    private java.util.Date date;
}
