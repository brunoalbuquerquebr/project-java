package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.form.VehicleForm;
import br.com.caelum.carangobom.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping()
    @ResponseBody
    @Transactional
    public ResponseEntity<VehicleForm> createVehicle(@Valid @RequestBody VehicleForm vehicleForm) {
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicleForm), null, HttpStatus.CREATED);
    }

    @GetMapping()
    @ResponseBody
    @Transactional
    public ResponseEntity<List<VehicleForm>> listVehicles() {
        return new ResponseEntity<>(vehicleService.listVehicles(),null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<VehicleForm> listVehicleById(@PathVariable Long id) {
        VehicleForm vehicleForm = vehicleService.vehicleFindById(id);
        if(vehicleForm != null) {
            return new ResponseEntity<>(vehicleForm,null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(vehicleForm,null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<VehicleForm> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleForm vehicleForm) {
        VehicleForm vehicleUpdate = vehicleService.updateVehicle(id, vehicleForm);
        if(vehicleUpdate != null) {
            return new ResponseEntity<>(vehicleUpdate, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(vehicleUpdate, null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<VehicleForm> deleteVehicle(@PathVariable Long id) {
        VehicleForm deleteVehicle = vehicleService.deleteVehicle(id);
        if(deleteVehicle != null) {
            return new ResponseEntity<>(deleteVehicle, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(deleteVehicle, null, HttpStatus.NOT_FOUND);
        }
    }

}
