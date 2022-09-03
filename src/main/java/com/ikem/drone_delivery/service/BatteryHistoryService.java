package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.BatteryHistoryDto;
import com.ikem.drone_delivery.dto.MedicationDto;

import java.util.List;

public interface BatteryHistoryService {
    List<BatteryHistoryDto> getBatteryHistoriesForDrone(String droneSerialNo);

}
