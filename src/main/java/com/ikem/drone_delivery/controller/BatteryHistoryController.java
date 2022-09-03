package com.ikem.drone_delivery.controller;

import com.ikem.drone_delivery.dto.BatteryHistoryDto;
import com.ikem.drone_delivery.service.BatteryHistoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

import static com.ikem.drone_delivery.util.Endpoints.BATTERY_HISTORY_BASE_URL;

@RestController
@AllArgsConstructor
@RequestMapping(BATTERY_HISTORY_BASE_URL)
public class BatteryHistoryController {

    private BatteryHistoryService batteryHistoryService;

    @GetMapping("/{droneSerialNo}")
    public ResponseEntity<List<BatteryHistoryDto>> getBatteryHistoriesForDrone(
            @PathVariable @Size(max = 100, message = "Serial number must be 100 characters max.") String droneSerialNo
    ){
        return ResponseEntity.ok(batteryHistoryService.getBatteryHistoriesForDrone(droneSerialNo));
    }
}
