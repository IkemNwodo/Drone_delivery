package com.ikem.drone_delivery.controller;

import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class MedicationController {

    private MedicationService medicationService;

    @GetMapping("/drones/{droneSerialNo}/medications")
    public ResponseEntity<List<MedicationDto>> getMedications(
            @PathVariable @Size(max = 100, message = "Serial number must be 100 characters max.") String droneSerialNo
    ) {
        return ResponseEntity.ok(medicationService.getMedicationsForDrone(droneSerialNo));
    }
}
