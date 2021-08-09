package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.form.BrandForm;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BrandControllerTest {

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

    @Test
    @Order(1)
    void contextLoads() {
        Assertions.assertTrue(true);
    }

    @Test
    @Order(2)
    void createBrand() {
        ResponseEntity<BrandForm> response = brandController.createBrand(createBrandForm("Toyota"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(3)
    void returnAllBrand() {
        ResponseEntity<List<BrandForm>> response = brandController.listBrand();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(4)
    void returnBrandForId() {
        Integer id = 1;
        ResponseEntity<BrandForm> response = brandController.listBrandById(id.longValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(5)
    void returnBrandForIdNull() {
        Integer id = 8;
        ResponseEntity<BrandForm> response = brandController.listBrandById(id.longValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Order(6)
    void returnUpdateBrand() {
        Integer id = 1;
        BrandForm newBrand = createBrandForm("JavaScript");
        ResponseEntity<BrandForm> response = brandController.updateBrand(id.longValue(), newBrand);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(7)
    void returnUpdateCourseNull() {
        Integer id = 7;
        BrandForm newBrand = createBrandForm("JavaScript");
        ResponseEntity<BrandForm> response = brandController.updateBrand(id.longValue(), newBrand);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

//    @Test
//    @Order(8)
//    void deleteVehicle() {
//        Integer id = 1;
//        ResponseEntity<BrandForm> response = brandController.deleteBrand(id.longValue());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    @Order(9)
    void deleteVehicleNull() {
        Integer id = 9;
        ResponseEntity<BrandForm> response = brandController.deleteBrand(id.longValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}