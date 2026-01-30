package br.com.alelofrota.domain.controller;

import br.com.alelofrota.api.exception.RoleException;
import com.google.common.base.Strings;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelofrota.domain.dto.VehicleDTO;
import br.com.alelofrota.domain.model.Vehicle;
import br.com.alelofrota.domain.service.VehicleService;
import br.com.alelofrota.domain.utilities.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicle", description = "API REST Alelo Frota 2020")
public class VehicleController {

	@Autowired
	private VehicleService serviceVehicle;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@Operation(summary = "Return all vehicles in pages",
			description = "Returns vehicles filtered by plate or status (active/inactive/all)")
	public ResponseEntity<Page<VehicleDTO>> find(@RequestParam(required = false) String filter, 
			@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
		if (Strings.isNullOrEmpty(filter)) {
			Page<VehicleDTO> list = serviceVehicle.findAll(PageRequest.of(page, size));
			return ResponseEntity.ok(list);
		}
		if (!Strings.isNullOrEmpty(filter) && "true".equals(filter)) {
			Page<VehicleDTO> list = serviceVehicle.findByStatus(true, PageRequest.of(page, size));
			return ResponseEntity.ok(list);
		}
		if (!Strings.isNullOrEmpty(filter) && "false".equals(filter)) {
			Page<VehicleDTO> list = serviceVehicle.findByStatus(false, PageRequest.of(page, size));
			return ResponseEntity.ok(list);
		}
		Page<VehicleDTO> list = serviceVehicle.findByPlateContains(Util.removeSpecialCharacters(filter).toUpperCase(), PageRequest.of(page, size));
		return ResponseEntity.ok(list);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	@Operation(summary = "Return vehicle by id")
	public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
		Vehicle vehicle = serviceVehicle.findById(id).orElseThrow(() -> new RoleException("Vehicle not found!"));
		return ResponseEntity.ok(new VehicleDTO(vehicle));
	}

	@PostMapping
	@Operation(summary = "Save Vehicle")
	public ResponseEntity<VehicleDTO> save(@Valid @RequestBody Vehicle vehicle) {
		if (serviceVehicle.existsVehicleWithPlate(vehicle.getPlate())) {
			throw new RoleException("This plate already exist!");
		}
		return new ResponseEntity<>(serviceVehicle.save(vehicle), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update Vehicle")
	public ResponseEntity<VehicleDTO> update(@Valid @RequestBody Vehicle vehicle, @PathVariable Long id) {
		Vehicle vehicleAux = serviceVehicle.findById(id).orElseThrow(() -> new RoleException("Vehicle not found!"));
		if (!vehicleAux.getPlate().equals(vehicle.getPlate())) {
			throw new RoleException("It isn't allowed to change a plate!");
		}
		return new ResponseEntity<>(serviceVehicle.save(vehicle), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Vehicle")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
		serviceVehicle.findById(id).orElseThrow(() -> new RoleException("Vehicle not found!"));
		serviceVehicle.delete(Vehicle.builder().id(id).build());
		return ResponseEntity.noContent().build();
	}

}
