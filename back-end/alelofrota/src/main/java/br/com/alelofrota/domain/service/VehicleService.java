package br.com.alelofrota.domain.service;

import java.util.List;

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
	VehicleRepository repository;
	
//	/vehicle?page=1&limit=10 Lista veiculos paginados
	@Transactional(readOnly = true)
	public Page<VehicleDTO> findAll(Pageable pageable){
		Page<Vehicle> result = repository.findAll(pageable);
		return result.map(v -> new VehicleDTO(v));
	}

//	/vehicle?filter=ABC4852 Busca veículo pela placa
	@Transactional(readOnly = true)
	public List<VehicleDTO> findByPlate(String plate) {
		return repository.findByPlate(plate);
	}

//	/vehicle?filter=true Lista veículos pelo status
	@Transactional(readOnly = true)
	public List<VehicleDTO> findByStatus(boolean status) {
		return repository.findByStatus(status);
	}
	
//	/vehicle/:id Busca um veículo específico
	@Transactional(readOnly = true)
	public VehicleDTO findById(Long id) {
		return new VehicleDTO(repository.findById(id).orElse(null));
	}
	
//	/vehicle Cria / atualizar um novo veículo
	public VehicleDTO save(Vehicle v) {
		return new VehicleDTO(repository.save(v));
	}
	
//	/vehicle/:id Remove um veículo
	public void delete(Vehicle v) {
		repository.delete(v);
	}
	
//	dbinit
	public void saveAll(List<Vehicle> list) {
		repository.saveAll(list);
	}

}
