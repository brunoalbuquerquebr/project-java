package br.com.caelum.carangobom.impl;

import br.com.caelum.carangobom.domain.Vehicle;
import br.com.caelum.carangobom.form.VehicleForm;
import br.com.caelum.carangobom.repository.VehicleRepository;
import br.com.caelum.carangobom.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
