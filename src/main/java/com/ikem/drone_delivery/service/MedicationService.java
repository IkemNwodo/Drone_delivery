package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.MedicationDto;

import java.util.List;

public interface MedicationService {

    List<MedicationDto> getMedicationsForDrone(String droneSerialNo);
}
