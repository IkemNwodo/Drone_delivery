package com.ikem.drone_delivery.service.impl;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.exception.DroneException;
import com.ikem.drone_delivery.exception.ResourceNotFoundException;
import com.ikem.drone_delivery.repository.DroneRepository;
import com.ikem.drone_delivery.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

    private DroneRepository droneRepository;

    private ModelMapper mapper;

    @Override
    public void registerDrone(DroneDto drone) {
        if (droneRepository.existsBySerialNo(drone.getSerialNo())){
            throw new DroneException(HttpStatus.BAD_REQUEST, "Drone already exists");
        }
        droneRepository.save(mapToDrone(drone));
    }

    @Override
    public Drone loadDroneWithMedication(String droneSerialNo, List<MedicationDto> medicationDtos) {
        Drone drone = droneRepository.findBySerialNo(droneSerialNo).orElseThrow(
                () -> new ResourceNotFoundException("Drone", "id", droneSerialNo));

        return null;
    }


    public DroneDto mapToDTO(Drone drone){
        return mapper.map(drone, DroneDto.class);
    }

    public Drone mapToDrone(DroneDto dto){
        return mapper.map(dto, Drone.class);
    }
}
