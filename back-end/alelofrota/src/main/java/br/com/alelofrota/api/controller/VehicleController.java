package br.com.alelofrota.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehicle")
@Api(value = "API REST Alelo Frota 2020")
public class VehicleController {

	@Autowired
	private VehicleService service;

	// http://localhost:8080/vehicle?page=1&size=10&sort=status,desc
	// http://localhost:8080/vehicle?filter=ABC4852
	// http://localhost:8080/vehicle?filter=true
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@ApiOperation(value = "Return all vehicles in pages")
	public ResponseEntity<Page<VehicleDTO>> find(@RequestParam(required = false) String filter, Pageable pageable) {
		if (filter == null) {
			Page<VehicleDTO> list = service.findAll(pageable);
			return ResponseEntity.ok(list);
		} else if (filter.toLowerCase().equals("active")) {
			Page<VehicleDTO> list = service.findByStatus(true, pageable);
			return ResponseEntity.ok(list);
		} else if (filter.toLowerCase().equals("inactive")) {
			Page<VehicleDTO> list = service.findByStatus(false, pageable);
			return ResponseEntity.ok(list);
		} else {
			Page<VehicleDTO> list = service.findByPlate(filter, pageable);
			return ResponseEntity.ok(list);
		}
	}

	// http://localhost:8080/vehicle/id
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Return vehicle by id")
	public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
		VehicleDTO v = service.findById(id);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		return ResponseEntity.notFound().build();
	}

	// http://localhost:8080/vehicle
	@PostMapping
	@ApiOperation(value = "Save Vehicle")
	public ResponseEntity<VehicleDTO> save(@Valid @RequestBody Vehicle v) {
		return new ResponseEntity<VehicleDTO>(service.save(v), HttpStatus.CREATED);
	}

	// http://localhost:8080/vehicle/id
	@PutMapping("/{id}")
	@ApiOperation(value = "Update Vehicle")
	public ResponseEntity<VehicleDTO> update(@Valid @RequestBody Vehicle v) {
		VehicleDTO vDTO = service.findById(v.getId());
		if (vDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<VehicleDTO>(service.save(v), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Vehicle")
	public ResponseEntity<Void> delete(@Valid @RequestBody Vehicle v) {
		VehicleDTO vDTO = service.findById(v.getId());
		if (vDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
