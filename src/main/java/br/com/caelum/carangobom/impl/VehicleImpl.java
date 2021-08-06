package br.com.caelum.carangobom.impl;

import br.com.caelum.carangobom.domain.Vehicle;
import br.com.caelum.carangobom.form.VehicleForm;
import br.com.caelum.carangobom.repository.VehicleRepository;
import br.com.caelum.carangobom.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleForm vehicleForm;

    @Override
    public VehicleForm saveVehicle(VehicleForm vehicleForm) {
        Vehicle vehicle = vehicleRepository.save(VehicleForm.convertTypeToDomain(vehicleForm));

        return  VehicleForm.convertDomainToType(vehicle);
    }

    @Override
    public List<VehicleForm> listVehicles() {
        List<Vehicle> listVehicles = vehicleRepository.findAll();
        return VehicleForm.convertDomainToDto(listVehicles);
    }

    @Override
    public VehicleForm updateVehicle(Long id, VehicleForm vehicleForm) {
        Optional<Vehicle> updateVehicle = vehicleRepository.findById(id);
        if(updateVehicle.isPresent()) {
            Vehicle vehicleSave = updateVehicle.get();
            vehicleSave.setModel(vehicleForm.getModel());
            vehicleSave.setYear(vehicleForm.getYear());
            vehicleSave.setPrice(vehicleForm.getPrice());

            return VehicleForm.convertDomainToType(vehicleRepository.saveAndFlush(vehicleSave));
        } else {
            return null;
        }
    }

    @Override
    public VehicleForm deleteVehicle(Long id) {
        Optional<Vehicle> deleteVehicle = vehicleRepository.findById(id);
        if(deleteVehicle.isPresent()) {
            Vehicle vehicle = deleteVehicle.get();
            vehicleRepository.delete(vehicle);
            return VehicleForm.convertDomainToType(vehicle);
        } else {
            return null;
        }
    }

    @Override
    public VehicleForm vehicleFindById(Long id) {
        Optional<Vehicle> findVehicleById = vehicleRepository.findById(id);
        if(findVehicleById.isPresent()) {
            return VehicleForm.convertDomainToType(findVehicleById.get());
        } else {
            return null;
        }
    }
}
