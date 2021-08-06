package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.form.VehicleForm;
import br.com.caelum.carangobom.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/vehicle")

public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping()
    @ResponseBody
    @Transactional
    public ResponseEntity<VehicleForm> createVehicle(@Valid @RequestBody VehicleForm vehicleForm) {
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicleForm), null, HttpStatus.CREATED);
    }
}
