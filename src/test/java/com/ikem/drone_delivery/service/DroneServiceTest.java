package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.DroneResponse;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.exception.DroneException;
import com.ikem.drone_delivery.exception.ResourceNotFoundException;
import com.ikem.drone_delivery.repository.DroneRepository;
import com.ikem.drone_delivery.util.DroneState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@Transactional
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@SpringBootTest
public class DroneServiceTest {

    @Autowired
    private DroneRepository droneRepository;

    private ModelMapper mapper = new ModelMapper();

    private static final String DRONE_SERIAL_NO = "ABC006";

    @Autowired
    private DroneService droneService;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManager.persist(mapper.map(getDroneDtoObject(), Drone.class));
        entityManager.flush();
    }

    DroneDto getDroneDtoObject() {
        return DroneDto.builder()
                .serialNo(DRONE_SERIAL_NO)
                .batteryCapacity(50.0)
                .weightLimit(100.0)
                .build();
    }

    @Nested
    class whenRegisteringDrone {
        @Test
        public void registerDrone() {
            String serialNo = "ABC007";
            DroneDto droneDto = DroneDto.builder()
                    .serialNo(serialNo)
                    .weightLimit(100.0)
                    .build();

            droneService.registerDrone(droneDto);
            assertTrue(droneRepository.findBySerialNo(serialNo).isPresent());
        }
    }

    @Nested
    class whenLoadingDrone {
        @Test
        public void loadDroneWithMedication() {

            List<MedicationDto> medicationDtos = List.of(MedicationDto.builder()
                    .weight(50.0)
                    .build());

            droneService.loadDroneWithMedication(DRONE_SERIAL_NO, medicationDtos);
            assertEquals(1, droneRepository.findBySerialNo(DRONE_SERIAL_NO).get().getMedications().size());
        }

        @Test
        public void check_if_medication_weight_is_more_drone() {
            MedicationDto medicationDto = MedicationDto.builder()
                    .weight(500.0)
                    .build();

            assertThatThrownBy(() -> droneService.loadDroneWithMedication(getDroneDtoObject().getSerialNo(), List.of(medicationDto)))
                    .isInstanceOf(DroneException.class)
                    .hasMessageContaining("Too much weight for drone");
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
    }

    @Nested
    class whenGettingDrones {
        @Test
        public void getAvailableDronesForLoading() {
            int pageNo = 1;
            int pageSize = 5;
            droneService.getAvailableDronesForLoading(pageNo, pageSize);
            verify(droneRepository, atLeastOnce()).findDronesByStateEquals(DroneState.IDLE, PageRequest.of(pageNo, pageSize));
            assertThat(droneService.getAvailableDronesForLoading(pageNo, pageSize)).isExactlyInstanceOf(DroneResponse.class);
        }

        @Test
        public void getAllDrones() {
            int pageNo = 1;
            int pageSize = 5;
            droneService.getAllDrones(pageNo, pageSize);
            assertThat(droneService.getAvailableDronesForLoading(pageNo, pageSize)).isExactlyInstanceOf(DroneResponse.class);
        }
    }

    @Test
    void getMedicationsForDrone() {
        assertThat(medicationService.getMedicationsForDrone(DRONE_SERIAL_NO).size()).isEqualTo(1);
    }

    @Test
    public void getBatteryLevel() {
        assertThat(droneService.getBatteryLevel(DRONE_SERIAL_NO)).isEqualTo("50");
    }
}