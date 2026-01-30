package br.com.alelofrota.api.test;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import br.com.alelofrota.domain.controller.VehicleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelofrota.domain.dto.VehicleDTO;
import br.com.alelofrota.domain.model.Vehicle;
import br.com.alelofrota.domain.service.VehicleService;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VehicleService vehicleService;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	void shouldReturnAllVehiclesWhenFilterIsNull() throws Exception {
		Page<VehicleDTO> page = new PageImpl<>(List.of(new VehicleDTO()));
		when(vehicleService.findAll(any())).thenReturn(page);

		mockMvc.perform(get("/vehicle"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content", hasSize(1)));

		verify(vehicleService).findAll(any());
	}

	@Test
	void shouldReturnActiveVehiclesWhenFilterTrue() throws Exception {
		Page<VehicleDTO> page = new PageImpl<>(List.of(new VehicleDTO()));
		when(vehicleService.findByStatus(eq(true), any())).thenReturn(page);

		mockMvc.perform(get("/vehicle")
						.param("filter", "true"))
				.andExpect(status().isOk());

		verify(vehicleService).findByStatus(eq(true), any());
	}

	@Test
	void shouldReturnInactiveVehiclesWhenFilterFalse() throws Exception {
		Page<VehicleDTO> page = new PageImpl<>(List.of(new VehicleDTO()));
		when(vehicleService.findByStatus(eq(false), any())).thenReturn(page);

		mockMvc.perform(get("/vehicle")
						.param("filter", "false"))
				.andExpect(status().isOk());

		verify(vehicleService).findByStatus(eq(false), any());
	}

	@Test
	void shouldReturnVehiclesByPlateFilter() throws Exception {
		Page<VehicleDTO> page = new PageImpl<>(List.of(new VehicleDTO()));
		when(vehicleService.findByPlateContains(eq("ABC1234"), any()))
				.thenReturn(page);

		mockMvc.perform(get("/vehicle")
						.param("filter", "abc-1234"))
				.andExpect(status().isOk());

		verify(vehicleService).findByPlateContains(eq("ABC1234"), any());
	}

	/* -------------------- FIND BY ID -------------------- */

	@Test
	void shouldFindVehicleById() throws Exception {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1L);

		when(vehicleService.findById(1L)).thenReturn(Optional.of(vehicle));

		mockMvc.perform(get("/vehicle/{id}", 1L))
				.andExpect(status().isOk());

		verify(vehicleService).findById(1L);
	}

	@Test
	void shouldThrowExceptionWhenVehicleNotFoundById() throws Exception {
		when(vehicleService.findById(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/vehicle/{id}", 1L))
				.andExpect(status().isNotFound());
	}

	/* -------------------- SAVE -------------------- */

	@Test
	void shouldSaveVehicleSuccessfully() throws Exception {
		Vehicle vehicle = Vehicle.builder()
				.plate("abc-1234")
				.model("Gol")
				.manufacturer("Volkswagen")
				.color("Preto")
				.status(true)
				.build();

		VehicleDTO dto = new VehicleDTO();

		when(vehicleService.existsVehicleWithPlate("ABC1234")).thenReturn(false);
		when(vehicleService.save(any())).thenReturn(dto);

		mockMvc.perform(post("/vehicle")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(vehicle)))
				.andExpect(status().isCreated());

		verify(vehicleService).save(any());
	}

	@Test
	void shouldNotSaveVehicleWhenPlateAlreadyExists() throws Exception {
		Vehicle vehicle = new Vehicle();
		vehicle.setPlate("ABC1234");

		when(vehicleService.existsVehicleWithPlate("ABC1234")).thenReturn(true);

		mockMvc.perform(post("/vehicle")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(vehicle)))
				.andExpect(status().isBadRequest());
	}

	/* -------------------- UPDATE -------------------- */

	@Test
	void shouldUpdateVehicleSuccessfully() throws Exception {
		Vehicle existing = Vehicle.builder()
				.id(1L)
				.plate("ABC1234")
				.model("Gol")
				.manufacturer("Volkswagen")
				.color("Preto")
				.status(true)
				.build();

		Vehicle updated = Vehicle.builder()
				.plate("ABC1234")
				.model("Gol")
				.manufacturer("Volkswagen")
				.color("Preto")
				.status(true)
				.build();

		when(vehicleService.findById(1L)).thenReturn(Optional.of(existing));
		when(vehicleService.save(any())).thenReturn(new VehicleDTO());

		mockMvc.perform(put("/vehicle/{id}", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updated)))
				.andExpect(status().isCreated());

		verify(vehicleService).save(any());
	}

	@Test
	void shouldThrowExceptionWhenChangingPlateOnUpdate() throws Exception {
		Vehicle existing = new Vehicle();
		existing.setId(1L);
		existing.setPlate("ABC1234");

		Vehicle updated = new Vehicle();
		updated.setPlate("XYZ9999");

		when(vehicleService.findById(1L)).thenReturn(Optional.of(existing));

		mockMvc.perform(put("/vehicle/{id}", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updated)))
				.andExpect(status().isBadRequest());
	}

	/* -------------------- DELETE -------------------- */

	@Test
	void shouldDeleteVehicleSuccessfully() throws Exception {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1L);

		when(vehicleService.findById(1L)).thenReturn(Optional.of(vehicle));

		mockMvc.perform(delete("/vehicle/{id}", 1L))
				.andExpect(status().isNoContent());

		verify(vehicleService).delete(any());
	}

	@Test
	void shouldThrowExceptionWhenDeletingNonExistingVehicle() throws Exception {
		when(vehicleService.findById(1L)).thenReturn(Optional.empty());

		mockMvc.perform(delete("/vehicle/{id}", 1L))
				.andExpect(status().isNotFound());
	}
}
