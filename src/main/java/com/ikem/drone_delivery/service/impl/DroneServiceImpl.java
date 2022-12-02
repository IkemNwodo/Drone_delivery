package com.ikem.drone_delivery.service.impl;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.DroneResponse;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.entity.Medication;
import com.ikem.drone_delivery.exception.DroneException;
import com.ikem.drone_delivery.exception.ResourceNotFoundException;
import com.ikem.drone_delivery.repository.DroneRepository;
import com.ikem.drone_delivery.service.DroneService;
import com.ikem.drone_delivery.util.AppConstants;
import com.ikem.drone_delivery.util.DroneState;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DroneServiceImpl implements DroneService {


    private DroneRepository droneRepository;

    private ModelMapper mapper;

    public DroneServiceImpl(DroneRepository droneRepository, ModelMapper mapper) {
        this.droneRepository = droneRepository;
        this.mapper = mapper;
    }

    @Override
    public void registerDrone(DroneDto drone) {

        if (droneRepository.existsBySerialNo(drone.getSerialNo())) {
            log.info("Drone {} already exists", drone);
            throw new DroneException(HttpStatus.BAD_REQUEST, "Drone already exists");
        }
        if (droneRepository.count() == AppConstants.DRONES_IN_FLEET){
            log.error("You can't add more than 10 drone in this fleet.");
            throw new DroneException(HttpStatus.EXPECTATION_FAILED, "You can't add more than 10 drone in this fleet.");
        }
            droneRepository.save(mapToDrone(drone));
    }

    @Override
    public void loadDroneWithMedication(String droneSerialNo, List<MedicationDto> medicationDtos) {
        Drone drone = droneRepository.findBySerialNo(droneSerialNo).orElseThrow(
                () -> new ResourceNotFoundException("Drone", "serial No.", droneSerialNo));

        double weightsOfMedications = medicationDtos.stream().mapToDouble(MedicationDto::getWeight).sum();
        if (weightsOfMedications < drone.getWeightLimit()) {
            Set<Medication> medications = medicationDtos.stream().map(this::mapToMedication).collect(Collectors.toSet());
            medications.forEach( medication -> medication.setDrone(drone));
            drone.setMedications(medications);
            drone.setWeightLimit(weightsOfMedications);
            droneRepository.save(drone);
        } else {
            throw new DroneException(HttpStatus.EXPECTATION_FAILED, "Too much weight for drone");
        }
    }

    @Override
    public DroneResponse getAvailableDronesForLoading(int pageNo, int pageSize) {
        Page<Drone> availableDrones = Page.empty();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        try {
            availableDrones = droneRepository.findDronesByStateEquals(DroneState.IDLE, pageable);

            log.info("DroneServiceImpl: {}", availableDrones.getContent());
        } catch (Exception e) {
            log.error("Error occurred while fetching");
        }

        return DroneResponse.builder()
                .isEmpty(availableDrones.isEmpty())
                .content(availableDrones.stream().map(this::mapToDroneDTO).collect(Collectors.toSet()))
                .pageNo(availableDrones.getNumber())
                .pageSize(availableDrones.getSize())
                .totalElements(availableDrones.getTotalElements())
                .totalPages(availableDrones.getTotalPages())
                .isLast(availableDrones.isLast())
                .build();
    }

    @Override
    public DroneResponse getAllDrones(int pageNo, int pageSize) {
        Page<Drone> drones = Page.empty();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        try {
            drones = droneRepository.findAll(pageable);

        } catch (Exception e) {
            log.error("Error occurred while fetching");
        }

        return DroneResponse.builder()
                .isEmpty(drones.isEmpty())
                .content(drones.stream().map(this::mapToDroneDTO).collect(Collectors.toSet()))
                .pageNo(drones.getNumber())
                .pageSize(drones.getSize())
                .totalElements(drones.getTotalElements())
                .totalPages(drones.getTotalPages())
                .isLast(drones.isLast())
                .build();
    }

    @Override
    public String getBatteryLevel(String droneSerialNo) {

        String batteryLevel = null;
        try {
            Drone drone = droneRepository.findBySerialNo(droneSerialNo).orElseThrow(
                    () -> new ResourceNotFoundException("Drone", "serial No.", droneSerialNo));
            batteryLevel = String.valueOf(drone.getBatteryCapacity());
        } catch (Exception e){
            log.error("Error occurred while fetching");
        }

        return batteryLevel;
    }

    public DroneDto mapToDroneDTO(Drone drone) {
        return mapper.map(drone, DroneDto.class);
    }

    public Drone mapToDrone(DroneDto dto) {
        return mapper.map(dto, Drone.class);
    }

    public MedicationDto mapToMedicationDTO(Medication medication) {
        return mapper.map(medication, MedicationDto.class);
    }

    public Medication mapToMedication(MedicationDto dto) {
        return mapper.map(dto, Medication.class);
    }
}
