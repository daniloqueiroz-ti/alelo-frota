package br.com.alelofrota.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelofrota.domain.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	List<Vehicle> findByPlate(String plate);
	
	List<Vehicle> findByStatus(boolean status);

}
