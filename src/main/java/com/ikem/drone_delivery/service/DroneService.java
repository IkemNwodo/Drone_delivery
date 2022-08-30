package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.DroneResponse;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.entity.Drone;

import java.util.List;

public interface DroneService {

    void registerDrone(DroneDto drone);

    void loadDroneWithMedication(String droneSerialNo, List<MedicationDto> medicationDtos);

    DroneResponse getAvailableDronesForLoading(int pageNo, int pageSize);

    DroneResponse getAllDrones(int pageNo, int pageSize);

    String getBatteryLevel(String serialNo);
}
