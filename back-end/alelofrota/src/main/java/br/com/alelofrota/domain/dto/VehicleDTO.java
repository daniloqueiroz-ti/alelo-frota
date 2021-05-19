package br.com.alelofrota.domain.dto;

import java.io.Serializable;

import br.com.alelofrota.domain.model.Vehicle;
import lombok.Data;

@Data
public class VehicleDTO implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String plate;
	private String model;
	private String manufacturer;
	private String color;
	private boolean status;
	
	
	public VehicleDTO(long id, String plate, String model, String manufacturer, String color, boolean status) {
		this.id = id;
		this.plate = plate;
		this.model = model;
		this.manufacturer = manufacturer;
		this.color = color;
		this.status = status;
	}
	
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
