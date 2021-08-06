package br.com.caelum.carangobom.service;

import br.com.caelum.carangobom.form.VehicleForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    public VehicleForm saveVehicle(VehicleForm vehicleForm);

    public List<VehicleForm> listVehicles();

    public VehicleForm updateVehicle(Long id, VehicleForm vehicleForm);

    public VehicleForm deleteVehicle(Long id);

    public VehicleForm vehicleFindById(Long id);
}
