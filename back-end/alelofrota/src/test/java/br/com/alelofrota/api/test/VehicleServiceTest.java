package br.com.alelofrota.api.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import br.com.alelofrota.domain.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alelofrota.domain.dto.VehicleDTO;
import br.com.alelofrota.domain.model.Vehicle;
import br.com.alelofrota.domain.repository.VehicleRepository;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    /* -------------------- helpers -------------------- */

    private Vehicle vehicle(String plate, boolean status) {
        return Vehicle.builder()
                .plate(plate)
                .model("Gol")
                .manufacturer("Volkswagen")
                .color("Preto")
                .status(status)
                .build();
    }

    /* -------------------- all -------------------- */

    @Test
    void shouldReturnAllVehiclesPaged() {
        Vehicle v1 = Vehicle.builder()
                .id(1L)
                .plate("ABC1234")
                .model("Gol")
                .manufacturer("Volkswagen")
                .color("Preto")
                .status(true)
                .build();

        Page<Vehicle> page = new PageImpl<>(List.of(v1));

        when(vehicleRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<VehicleDTO> result = vehicleService.findAll(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getId());
    }


    /* -------------------- withPlateContains -------------------- */

    @Test
    void shouldReturnVehiclesFilteredByPlate() {
        Vehicle v1 = Vehicle.builder()
                .id(2L)
                .plate("ABC")
                .model("Gol")
                .manufacturer("Volkswagen")
                .color("Preto")
                .status(true)
                .build();

        Page<Vehicle> page = new PageImpl<>(List.of(v1));

        when(vehicleRepository.findByPlateContains(eq("ABC"), any(Pageable.class)))
                .thenReturn(page);

        Page<VehicleDTO> result = vehicleService.findByPlateContains("ABC", PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("ABC", result.getContent().get(0).getPlate());
    }


    /* -------------------- existsVehicleWithPlate -------------------- */

    @Test
    void shouldReturnTrueWhenVehicleExistsByPlate() {
        when(vehicleRepository.existsVehicleByPlate("ABC1234")).thenReturn(true);

        boolean exists = vehicleService.existsVehicleWithPlate("ABC1234");

        assertThat(exists).isTrue();
        verify(vehicleRepository).existsVehicleByPlate("ABC1234");
    }

    /* -------------------- withStatus -------------------- */

    @Test
    void shouldReturnVehiclesByStatus() {
        Vehicle v1 = Vehicle.builder()
                .id(3L)
                .plate("DEF1234")
                .model("Golf")
                .manufacturer("Volkswagen")
                .color("Cinza")
                .status(true)
                .build();

        Page<Vehicle> page = new PageImpl<>(List.of(v1));

        when(vehicleRepository.findByStatus(eq(true), any(Pageable.class)))
                .thenReturn(page);

        Page<VehicleDTO> result = vehicleService.findByStatus(true, PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertTrue(result.getContent().get(0).isStatus());
    }


    /* -------------------- withId -------------------- */

    @Test
    void shouldReturnVehicleWhenIdExists() {
        Vehicle vehicle = vehicle("ABC1234", true);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        Optional<Vehicle> result = vehicleService.findById(1L);

        assertThat(result).isPresent();
        verify(vehicleRepository).findById(1L);
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        when(vehicleRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Vehicle> result = vehicleService.findById(1L);

        assertThat(result).isEmpty();
        verify(vehicleRepository).findById(1L);
    }

    /* -------------------- saveVehicle -------------------- */

    @Test
    void shouldSaveVehicleAndReturnDTO() {
        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .plate("ABC1234")
                .model("Gol")
                .manufacturer("Volkswagen")
                .color("Preto")
                .status(true)
                .build();

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        VehicleDTO dto = vehicleService.save(vehicle);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("ABC1234", dto.getPlate());
    }
    /* -------------------- deleteVehicle -------------------- */

    @Test
    void shouldDeleteVehicle() {
        Vehicle vehicle = vehicle("ABC1234", true);

        vehicleService.delete(vehicle);

        verify(vehicleRepository).delete(vehicle);
    }

    /* -------------------- saveAllVehicles -------------------- */

    @Test
    void shouldSaveAllVehicles() {
        List<Vehicle> list = List.of(
                vehicle("ABC1234", true),
                vehicle("XYZ9999", false)
        );

        vehicleService.saveAll(list);

        verify(vehicleRepository).saveAll(list);
    }
}

