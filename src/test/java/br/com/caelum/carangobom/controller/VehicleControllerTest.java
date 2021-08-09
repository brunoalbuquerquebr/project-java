package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.form.BrandForm;
import br.com.caelum.carangobom.form.VehicleForm;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VehicleControllerTest {

    @Autowired
    private VehicleController vehicleController;

    @Autowired
    private BrandController brandController;

    @BeforeEach
    void configuraMock() {
        openMocks(this);
    }

    private BrandForm createBrandForm(String name) {
        BrandForm newBrand = new BrandForm();
        newBrand.setName(name);
        return newBrand;
    }

    private VehicleForm createVehicleForm() {
        VehicleForm vehicleForm = new VehicleForm();
        vehicleForm.setModel("Fusca");
        Integer year = 1975;
        Integer price = 5500;
        vehicleForm.setYear(year.longValue());
        vehicleForm.setPrice(price.doubleValue());

        BrandForm brandForm = new BrandForm();
        Integer id = 1;
        brandForm.setId(id.longValue());
        vehicleForm.setBrand(brandForm);

        return vehicleForm;
    }

    @Test
    @Order(1)
    void createBrand() {
        ResponseEntity<BrandForm> response = brandController.createBrand(createBrandForm("Toyota"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    void contextLoads() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(3)
    void createVehicle() {
        ResponseEntity<VehicleForm> response = vehicleController.createVehicle(createVehicleForm());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(4)
    void returnAllVehicle() {
        ResponseEntity<List<VehicleForm>> response = vehicleController.listVehicles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(5)
    void returnVehicleForId() {
        Integer id = 1;
        ResponseEntity<VehicleForm> response = vehicleController.listVehicleById(id.longValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(6)
    void returnVehicleForIdNull() {
        Integer id = 99;
        ResponseEntity<VehicleForm> response = vehicleController.listVehicleById(id.longValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Order(7)
    void returnUpdateVehicle() {
        Integer id = 1;
        VehicleForm newVehicle = createVehicleForm();
        ResponseEntity<VehicleForm> response = vehicleController.updateVehicle(id.longValue(), newVehicle);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(8)
    void returnUpdateVehicleNull() {
        Integer id = 99;
        VehicleForm newVehicle = createVehicleForm();
        ResponseEntity<VehicleForm> response = vehicleController.updateVehicle(id.longValue(), newVehicle);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Order(9)
    void deleteVehicle() {
        Integer id = 1;
        ResponseEntity<VehicleForm> response = vehicleController.deleteVehicle(id.longValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(10)
    void deleteVehicleNull() {
        Integer id = 9;
        ResponseEntity<VehicleForm> response = vehicleController.deleteVehicle(id.longValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}