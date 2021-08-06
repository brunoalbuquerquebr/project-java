package br.com.caelum.carangobom.form;

import br.com.caelum.carangobom.domain.Brand;
import br.com.caelum.carangobom.domain.Vehicle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


@Component
@Getter
@Setter
public class VehicleForm {

    private Long id;

    private String model;

    private Long year;

    private Double price;

    private BrandForm brand;

    public static List<VehicleForm> convertDomainToDto(List<Vehicle> vehicle) {
        List<VehicleForm> listType = new ArrayList<>();
        vehicle.forEach(vehicles -> listType.add(convertDomainToType(vehicles)));
        return listType;
    }

    public static VehicleForm convertDomainToType(Vehicle vehicles) {
        VehicleForm vehicleForm = new VehicleForm();
        vehicleForm.setId(vehicles.getId());
        vehicleForm.setModel(vehicles.getModel());
        vehicleForm.setYear(vehicles.getYear());
        vehicleForm.setPrice(vehicles.getPrice());

        BrandForm brandForm = new BrandForm();
        brandForm.setId(vehicles.getBrand().getId());
        brandForm.setName(vehicles.getBrand().getName());
        vehicleForm.setBrand(brandForm);

        return vehicleForm;
    }

    public static Vehicle convertTypeToDomain(VehicleForm vehicleForm) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleForm.getId());
        vehicle.setModel(vehicleForm.getModel());
        vehicle.setPrice(vehicleForm.getPrice());
        vehicle.setYear(vehicleForm.getYear());

        Brand brand = new Brand();
        brand.setId(vehicleForm.getBrand().getId());
        vehicle.setBrand(brand);

        return vehicle;
    }



//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getModelVehicle() {
//        return modelVehicle;
//    }
//
//    public void setModelVehicle(String modelVehicle) {
//        this.modelVehicle = modelVehicle;
//    }
//
//    public Long getYearVehicle() {
//        return yearVehicle;
//    }
//
//    public void setYearVehicle(Long yearVehicle) {
//        this.yearVehicle = yearVehicle;
//    }
//
//    public Double getPriceVehicle() {
//        return priceVehicle;
//    }
//
//    public void setPriceVehicle(Double priceVehicle) {
//        this.priceVehicle = priceVehicle;
//    }
//
//    public Brand getBrand() {
//        return brand;
//    }
//
//    public void setBrand(Brand brand) {
//        this.brand = brand;
//    }
}
