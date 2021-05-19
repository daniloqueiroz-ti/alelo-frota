package br.com.alelofrota.domain.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import br.com.alelofrota.domain.model.Vehicle;

public class Dbinit implements CommandLineRunner {
	
	@Autowired
	VehicleService service;
	
	@Override
	public void run(String... args) throws Exception {

		Vehicle v1 = new Vehicle("ABC-1234", "Class C 1.1 Avantgarde Turbo Flex", "blue", "blue", true); 
		Vehicle v2 = new Vehicle("ABC-1112", "Class C 1.1 Avantgarde Turbo", "Mercedes-Benz", "blue", true);
		Vehicle v3 = new Vehicle("ABC-3344", "Class C 1.1 Avantgarde Flex", "Mercedes-Benz", "blue", true);
		Vehicle v4 = new Vehicle("ABC-5567", "Class C 1.1 Avantgarde Turbo", "Mercedes-Benz", "blue", true);
		Vehicle v5 = new Vehicle("ABC-2541", "Class C 1.1 Avantgarde Flex", "Mercedes-Benz", "blue", true);
		Vehicle v6 = new Vehicle("ABC-5679", "Golf 2.0 Turbo", "Mercedes-Benz", "gray", false);
		Vehicle v7 = new Vehicle("BCA-4852", "Golf 2.0 Flex", "Mercedes-Benz", "gray", false);
		Vehicle v8 = new Vehicle("BCA-6985", "Golf 2.0 Turbo Flex", "Chevrolet", "gray", true);
		Vehicle v9 = new Vehicle("BCA-2357", "Golf 2.0 Turbo Flex", "Chevrolet", "gray", true);
		Vehicle v10 = new Vehicle("BCA-1578", "Golf 2.0 Flex", "Chevrolet", "gray", true);
		Vehicle v11 = new Vehicle("BCA-2456", "Golf 2.0 Turbo Flex", "Chevrolet", "gray", true);
		Vehicle v12 = new Vehicle("BCA-7531", "Class C 1.1 Avantgarde Turbo Flex", "Chevrolet", "gray", true);
		Vehicle v13 = new Vehicle("BCA-1596", "Agile 1.4 Flex", "Chevrolet", "red", true);
		Vehicle v14 = new Vehicle("CBA-4455", "Agile 1.4 Turbo Flex", "Chevrolet", "red", true);
		Vehicle v15 = new Vehicle("CBA-5522", "Agile 1.4 Flex", "Chevrolet", "red", true);
		Vehicle v16 = new Vehicle("CBA-3366", "Agile 1.4 Turbo", "Chevrolet", "red", false);
		Vehicle v17 = new Vehicle("CBA-5588", "Agile 1.4 Flex", "Fiat", "red", false);
		Vehicle v18 = new Vehicle("CBA-6699", "Citron C3 Turbo", "Fiat", "red", false);
		Vehicle v19 = new Vehicle("CBA-5577", "Class C 1.1 Avantgarde Flex", "Fiat", "black", false);
		Vehicle v20 = new Vehicle("CBA-5522", "Class C 1.1 Avantgarde Turbo", "Fiat", "black", false);
		Vehicle v21 = new Vehicle("DFG-3366", "Citron C3 Flex", "Fiat", "black", true);
		Vehicle v22 = new Vehicle("DFG-9955", "Citron C3 Flex", "Fiat", "black", true);
		Vehicle v23 = new Vehicle("DFG-9875", "Citron C3 Flex", "Fiat", "black", true);
		
		service.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23));
	}

}
