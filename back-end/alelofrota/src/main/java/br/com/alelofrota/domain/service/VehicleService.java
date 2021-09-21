package br.com.alelofrota.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelofrota.domain.dto.VehicleDTO;
import br.com.alelofrota.domain.model.Vehicle;
import br.com.alelofrota.domain.repository.AllVehicles;

@Service
public class VehicleService {

	@Autowired
	AllVehicles allVehicles;
	
	@Transactional(readOnly = true)
	public List<Vehicle> findAll() {
		return allVehicles.findAll();
	}

	@Transactional(readOnly = true)
	public Page<VehicleDTO> all(Pageable pageable) {
		Page<Vehicle> result = allVehicles.findAll(pageable);
		return result.map(v -> new VehicleDTO(v));
	}

	@Transactional(readOnly = true)
	public Page<VehicleDTO> withPlateContains(String plate, Pageable pageable) {
		Page<Vehicle> result = allVehicles.findByPlateContains(plate, pageable);
		return result.map(v -> new VehicleDTO(v));
	}

	@Transactional(readOnly = true)
	public boolean existsVehicleWithPlate(String plate) {
		return allVehicles.existsVehicleByPlate(plate);
	}

	@Transactional(readOnly = true)
	public Page<VehicleDTO> withStatus(boolean status, Pageable pageable) {
		Page<Vehicle> result = allVehicles.findByStatus(status, pageable);
		return result.map(v -> new VehicleDTO(v));
	}

	@Transactional(readOnly = true)
	public Optional<Vehicle> withId(Long id) {
		return allVehicles.findById(id);
	}

	public VehicleDTO saveVehicle(Vehicle v) {
		return new VehicleDTO(allVehicles.save(v));
	}

	public void deleteVehicle(Vehicle v) {
		allVehicles.delete(v);
	}

	public void saveAllVehicles(List<Vehicle> list) {
		allVehicles.saveAll(list);
	}
}
