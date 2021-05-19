package br.com.alelofrota.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelofrota.domain.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	Page<Vehicle> findByPlateContains(String plate, Pageable pageable);
	
	Page<Vehicle> findByStatus(boolean status, Pageable pageable);
	
	//Verify isExist
	boolean existsVehicleByPlate(String plate);
	
}
