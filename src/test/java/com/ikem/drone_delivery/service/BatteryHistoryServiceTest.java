package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.BatteryHistoryDto;
import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.exception.ResourceNotFoundException;
import com.ikem.drone_delivery.repository.BatteryHistoryRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@SpringBootTest
class BatteryHistoryServiceTest {

    @Autowired
    private BatteryHistoryRepository batteryHistoryRepository;

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BatteryHistoryService batteryHistoryService;

    @Autowired
    private ModelMapper mapper;

    @Test
    public void getBatteryHistoriesForDrone() {

        DroneDto drone = DroneDto.builder()
                .serialNo("ABC008")
                .build();

        entityManager.persist(mapper.map(drone, Drone.class));
        entityManager.flush();

        List<BatteryHistoryDto> result = batteryHistoryService.getBatteryHistoriesForDrone("ABC008");

        assertThat(0).isEqualTo(result.size());
    }

    @Test
    void throw_when_drone_is_not_found(){
        batteryHistoryService.getBatteryHistoriesForDrone(null);
        assertThatThrownBy(() -> batteryHistoryService.getBatteryHistoriesForDrone(null))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Battery history for drone not found with Serial No : 'null'");
    }
}