package com.ikem.drone_delivery.controller;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.DroneResponse;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.service.DroneService;
import com.ikem.drone_delivery.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

import static com.ikem.drone_delivery.util.Endpoints.*;

@RestController
@AllArgsConstructor
@RequestMapping(DRONE_BASE_URL)
public class DroneController {

    private DroneService droneService;

    @PostMapping(REGISTER_DRONE)
    public ResponseEntity<String> registerDrone(@RequestBody DroneDto drone){
        droneService.registerDrone(drone);
        return new ResponseEntity<>("Drone created successfully", HttpStatus.CREATED);
    }

    @PostMapping(LOAD_DRONE_WITH_MEDICATIONS)
    public ResponseEntity<String> loadDroneWithMedication(
            @PathVariable @Size(max = 100, message = "Serial number must be 100 characters max.") String serialNo,
            @RequestBody List<MedicationDto> medications
            ) {
        droneService.loadDroneWithMedication(serialNo, medications);
        return ResponseEntity.ok("Drone loaded successfully");
    }
    @GetMapping(AVAILABLE_DRONES)
    public ResponseEntity<DroneResponse> getAvailableDrones(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        return  ResponseEntity.ok(
                droneService.getAvailableDronesForLoading(pageNo, pageSize)
        );
    }

    @GetMapping
    public ResponseEntity<DroneResponse> getAllDrones(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        return ResponseEntity.ok(
                droneService.getAllDrones(pageNo, pageSize)
        );
    }

    @GetMapping(GET_BATTERY_LEVEL)
    public ResponseEntity<String> getBatteryLevel(
            @PathVariable @Size(max = 100, message = "Serial number must be 100 characters max.") String serialNo
            ){
        return ResponseEntity.ok(droneService.getBatteryLevel(serialNo));
    }

}
