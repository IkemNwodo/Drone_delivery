package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.entity.Drone;

import java.util.List;

public interface DroneService {

    void registerDrone(DroneDto drone);

    Drone loadDroneWithMedication(String droneSerialNo, List<MedicationDto> medicationDtos);
}
