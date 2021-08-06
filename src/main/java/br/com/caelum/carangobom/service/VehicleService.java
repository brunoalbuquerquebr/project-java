package br.com.caelum.carangobom.service;

import br.com.caelum.carangobom.form.VehicleForm;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

    public VehicleForm saveVehicle(VehicleForm vehicleForm);
}
