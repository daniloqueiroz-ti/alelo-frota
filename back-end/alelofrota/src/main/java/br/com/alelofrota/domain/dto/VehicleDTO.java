package br.com.alelofrota.domain.dto;

import java.io.Serial;
import java.io.Serializable;

import br.com.alelofrota.domain.model.Vehicle;
import lombok.Data;

@Data
public class VehicleDTO implements Serializable  {

	@Serial
	private static final long serialVersionUID = 1L;

	private long id;
	private String plate;
	private String model;
	private String manufacturer;
	private String color;
	private boolean status;

	public VehicleDTO(Vehicle v) {
		this.id = v.getId();
		this.plate = v.getPlate();
		this.model = v.getModel();
		this.manufacturer = v.getManufacturer();
		this.color = v.getColor();
		this.status = v.isStatus();
	}

	public VehicleDTO() {

	}

}
