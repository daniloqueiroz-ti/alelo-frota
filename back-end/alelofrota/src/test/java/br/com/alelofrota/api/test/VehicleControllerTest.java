package br.com.alelofrota.api.test;

//import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.alelofrota.api.controller.VehicleController;
import br.com.alelofrota.domain.exception.RoleException;
import br.com.alelofrota.domain.model.Vehicle;
import br.com.alelofrota.domain.service.VehicleService;
import io.restassured.http.ContentType;

@WebMvcTest
public class VehicleControllerTest {

	@Autowired
	private VehicleController controllerVehicle;

	@MockBean
	private VehicleService serviceVehicle;

	@BeforeEach
	public void setup() {
		// RestAssuredMockMvc.standaloneSetup(this.vehicleController);
		standaloneSetup(this.controllerVehicle);
	}

	@Test
	public void testReturnSucessWhenFindById() {
		
		when(this.serviceVehicle.withId(1L).orElseThrow(() -> new RoleException("Vehicle not found!"))).thenReturn(new Vehicle("ABC-1234", "Ford Ka 1.0", "Ford", "blue", true));

		// RestAssuredMockMvc.given();		
		given().accept(ContentType.JSON).when().get("/vehicle/{id}", 1L).then().statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void testReturnNotFoundWhenFindById() {
		
		when(this.serviceVehicle.withId(123L)).thenReturn(null);

		// RestAssuredMockMvc.given();		
		given().accept(ContentType.JSON).when().get("/vehicle/{id}", 123L).then().statusCode(HttpStatus.SC_NOT_FOUND);
	}
}
