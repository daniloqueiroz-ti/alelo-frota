package br.com.alelofrota.domain.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import br.com.alelofrota.domain.model.Vehicle;

@Service
public class Dbinit implements CommandLineRunner {

    @Autowired
    VehicleService service;

    @Override
    public void run(String... args) throws Exception {

        Vehicle v1 = Vehicle.builder()
                .plate("ABC1234")
                .model("Ford Ka 1.0")
                .manufacturer("Ford")
                .color("blue")
                .status(true)
                .build();

        Vehicle v2 = Vehicle.builder()
                .plate("ABC1112")
                .model("Class C 1.1 Avantgarde Turbo")
                .manufacturer("MercedesBenz")
                .color("blue")
                .status(true)
                .build();

        Vehicle v3 = Vehicle.builder()
                .plate("ABC3344")
                .model("Montana 1.6 Flex")
                .manufacturer("Chevrolet")
                .color("blue")
                .status(true)
                .build();

        Vehicle v4 = Vehicle.builder()
                .plate("ABC5567")
                .model("Montana 1.6 Turbo")
                .manufacturer("Chevrolet")
                .color("blue")
                .status(true)
                .build();

        Vehicle v5 = Vehicle.builder()
                .plate("ABC2541")
                .model("Montana 1.6")
                .manufacturer("Chevrolet")
                .color("blue")
                .status(true)
                .build();

        Vehicle v6 = Vehicle.builder()
                .plate("ABC5679")
                .model("Golf 2.0 Turbo")
                .manufacturer("Volkswagen")
                .color("gray")
                .status(false)
                .build();

        Vehicle v7 = Vehicle.builder()
                .plate("BCA4852")
                .model("Golf 2.0 Flex")
                .manufacturer("Volkswagen")
                .color("gray")
                .status(false)
                .build();

        Vehicle v8 = Vehicle.builder()
                .plate("BCA6985")
                .model("Golf 2.0 Turbo Flex")
                .manufacturer("Volkswagen")
                .color("gray")
                .status(true)
                .build();

        Vehicle v9 = Vehicle.builder()
                .plate("BCA2357")
                .model("Golf 2.0 Turbo Flex")
                .manufacturer("Volkswagen")
                .color("gray")
                .status(true)
                .build();

        Vehicle v10 = Vehicle.builder()
                .plate("BCA1578")
                .model("Golf 2.0 Flex")
                .manufacturer("Volkswagen")
                .color("gray")
                .status(true)
                .build();

        Vehicle v11 = Vehicle.builder()
                .plate("BCA2456")
                .model("Golf 2.0 Turbo Flex")
                .manufacturer("Volkswagen")
                .color("gray")
                .status(true)
                .build();

        Vehicle v12 = Vehicle.builder()
                .plate("BCA7531")
                .model("Ford Ka 1.0")
                .manufacturer("Ford")
                .color("gray")
                .status(true)
                .build();

        Vehicle v13 = Vehicle.builder()
                .plate("BCA1596")
                .model("Agile 1.4 Flex")
                .manufacturer("Chevrolet")
                .color("red")
                .status(true)
                .build();

        Vehicle v14 = Vehicle.builder()
                .plate("CBA4455")
                .model("Agile 1.4 Turbo Flex")
                .manufacturer("Chevrolet")
                .color("red")
                .status(true)
                .build();

        Vehicle v15 = Vehicle.builder()
                .plate("CBA5522")
                .model("Agile 1.4 Flex")
                .manufacturer("Chevrolet")
                .color("red")
                .status(true)
                .build();

        Vehicle v16 = Vehicle.builder()
                .plate("CBA3366")
                .model("Agile 1.4 Turbo")
                .manufacturer("Chevrolet")
                .color("red")
                .status(false)
                .build();

        Vehicle v17 = Vehicle.builder()
                .plate("CBA5588")
                .model("Agile 1.4 Flex")
                .manufacturer("Chevrolet")
                .color("red")
                .status(false)
                .build();

        Vehicle v18 = Vehicle.builder()
                .plate("CBA6699")
                .model("Citron C3 Turbo")
                .manufacturer("Citron")
                .color("red")
                .status(false)
                .build();

        Vehicle v19 = Vehicle.builder()
                .plate("CBA5577")
                .model("Class C 1.1 Flex")
                .manufacturer("MercedesBenz")
                .color("black")
                .status(false)
                .build();

        Vehicle v20 = Vehicle.builder()
                .plate("CBA5522")
                .model("Class C 1.1 Turbo")
                .manufacturer("MercedesBenz")
                .color("black")
                .status(false)
                .build();

        Vehicle v21 = Vehicle.builder()
                .plate("DFG3366")
                .model("Citron C3 Flex")
                .manufacturer("Citron")
                .color("black")
                .status(true)
                .build();

        Vehicle v22 = Vehicle.builder()
                .plate("DFG9955")
                .model("Citron C3")
                .manufacturer("Citron")
                .color("black")
                .status(true)
                .build();

        Vehicle v23 = Vehicle.builder()
                .plate("DFG9875")
                .model("Citron C3 Turbo")
                .manufacturer("Citron")
                .color("black")
                .status(true)
                .build();

        service.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23));

    }

}
