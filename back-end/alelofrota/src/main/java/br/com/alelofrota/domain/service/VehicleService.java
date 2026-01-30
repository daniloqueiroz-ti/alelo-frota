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
import br.com.alelofrota.domain.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	@Transactional(readOnly = true)
	public Page<VehicleDTO> findAll(Pageable pageable) {
		Page<Vehicle> result = vehicleRepository.findAll(pageable);
		return result.map(VehicleDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<VehicleDTO> findByPlateContains(String plate, Pageable pageable) {
		Page<Vehicle> result = vehicleRepository.findByPlateContains(plate, pageable);
		return result.map(VehicleDTO::new);
	}

	@Transactional(readOnly = true)
	public boolean existsVehicleWithPlate(String plate) {
		return vehicleRepository.existsVehicleByPlate(plate);
	}

	@Transactional(readOnly = true)
	public Page<VehicleDTO> findByStatus(boolean status, Pageable pageable) {
		Page<Vehicle> result = vehicleRepository.findByStatus(status, pageable);
		return result.map(VehicleDTO::new);
	}

	@Transactional(readOnly = true)
	public Optional<Vehicle> findById(Long id) {
		return vehicleRepository.findById(id);
	}

	public VehicleDTO save(Vehicle v) {
		return new VehicleDTO(vehicleRepository.save(v));
	}

	public void delete(Vehicle v) {
		vehicleRepository.delete(v);
	}

	public void saveAll(List<Vehicle> list) {
		vehicleRepository.saveAll(list);
	}
}
