package br.com.caelum.carangobom.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String model;

    @NotNull
    private Long year;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name="brand_id", nullable=false)
    private Brand brand;

//    public vehicle(Long id, String modelVehicle, Long yearVehicle, Double priceVehicle, Brand brand) {
//        this.id = id;
//        this.modelVehicle = modelVehicle;
//        this.yearVehicle = yearVehicle;
//        this.priceVehicle = priceVehicle;
//        this.brand = brand;
//    }

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
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
