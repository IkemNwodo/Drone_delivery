package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.exception.DroneException;
import com.ikem.drone_delivery.exception.ResourceNotFoundException;
import com.ikem.drone_delivery.repository.DroneRepository;
import com.ikem.drone_delivery.service.impl.DroneServiceImpl;
import com.ikem.drone_delivery.util.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


public class DroneServiceTest {

    //@Mock
    private DroneRepository droneRepository;

    private ModelMapper mapper = new ModelMapper();

    private static final String DRONE_SERIAL_NO = "ABC003";
    private DroneService droneService;

    @BeforeEach
    public void setUp() {
        droneRepository = Mockito.mock(DroneRepository.class);
        droneService = new DroneServiceImpl(droneRepository, mapper);
    }

    void givenDroneIsCreated() {
        when(droneRepository.save(mapper.map(getDroneDtoObject(), Drone.class))).thenReturn(mapper.map(getDroneDtoObject(), Drone.class));
    }

    DroneDto getDroneDtoObject(){
        return DroneDto.builder()
                .serialNo(DRONE_SERIAL_NO)
                .build();
    }

    @Test
    public void registerDrone() {
        droneService.registerDrone(getDroneDtoObject());
        Mockito.verify(droneRepository).save(mapper.map(getDroneDtoObject(), Drone.class));
    }

    @Test
    public void check_if_drone_exists() {
        when(droneRepository.existsBySerialNo(getDroneDtoObject().getSerialNo()))
                .thenReturn(true);
        assertThatThrownBy(() -> droneService.registerDrone(getDroneDtoObject()))
                .isInstanceOf(DroneException.class)
                .hasMessageContaining("Drone already exists");
    }

    @Test
    public void check_if_drone_limit_is_reached() {
        given(droneRepository.count())
                .willReturn(AppConstants.DRONES_IN_FLEET);
        assertThatThrownBy(() -> droneService.registerDrone(getDroneDtoObject()))
                .isInstanceOf(DroneException.class)
                .hasMessageContaining("You can't add more than 10 drone in this fleet.");
    }

    @Test
    public void loadDroneWithMedication() {

        List<MedicationDto> medicationDtos = List.of(MedicationDto.builder().build());

        droneService.loadDroneWithMedication(DRONE_SERIAL_NO, medicationDtos);
        givenDroneIsCreated();
        Mockito.verify(droneRepository).save(mapper.map(getDroneDtoObject(), Drone.class));
    }

    public void check_if_medication_weight_is_more_drone() {

    }

    @Test
    public void check_if_drone_exists_for_Medication_loading() {
        MedicationDto medicationDto = MedicationDto.builder()
                .weight(500.0)
                .build();
        when(droneRepository.existsBySerialNo(getDroneDtoObject().getSerialNo()))
                .thenReturn(true);
        assertThatThrownBy(() -> droneService.loadDroneWithMedication(getDroneDtoObject().getSerialNo(), List.of(medicationDto)))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Drone not found with serial No. : 'ABC003'");
    }

    @Test
    public void getAvailableDronesForLoading() {
    }

    @Test
    public void getAllDrones() {
    }

    @Test
    public void getBatteryLevel() {
    }
}