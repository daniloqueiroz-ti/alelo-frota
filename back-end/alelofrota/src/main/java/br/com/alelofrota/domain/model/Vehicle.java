package br.com.alelofrota.domain.model;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle implements Serializable  {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo plate não pode ser vazio.")
	@Size(min = 3, max = 20)
	private String plate;
	
	@NotBlank(message = "O campo model não pode ser vazio.")
	private String model;
	
	@NotBlank(message = "O campo manufacturer não pode ser vazio.")
	private String manufacturer;
	
	@NotBlank(message = "O campo color não pode ser vazio.")
	private String color;
	
	private boolean status = true;

}
