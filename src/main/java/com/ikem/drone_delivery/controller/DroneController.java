package com.ikem.drone_delivery.controller;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/drone")
public class DroneController {

    private DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDrone(@RequestBody DroneDto drone){
        droneService.registerDrone(drone);
        return new ResponseEntity<>("Drone created successfully", HttpStatus.CREATED);
    }

}
