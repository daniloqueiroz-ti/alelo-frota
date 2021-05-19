package br.com.alelofrota.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Vehicle implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O campo plate não pode ser vazio.")
	private String plate;
	
	@NotBlank(message = "O campo model não pode ser vazio.")
	private String model;
	
	@NotBlank(message = "O campo manufacturer não pode ser vazio.")
	private String manufacturer;
	
	@NotBlank(message = "O campo color não pode ser vazio.")
	private String color;
	
	private boolean status;

	public Vehicle(@NotBlank(message = "O campo plate não pode ser vazio.") String plate,
			@NotBlank(message = "O campo model não pode ser vazio.") String model,
			@NotBlank(message = "O campo manufacturer não pode ser vazio.") String manufacturer,
			@NotBlank(message = "O campo color não pode ser vazio.") String color, boolean status) {
		this.plate = plate;
		this.model = model;
		this.manufacturer = manufacturer;
		this.color = color;
		this.status = status;
	}

	public Vehicle() {
		
	}
	
}
