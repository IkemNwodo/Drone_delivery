package com.ikem.drone_delivery.service;

import com.ikem.drone_delivery.dto.BatteryHistoryDto;
import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.entity.BatteryHistory;
import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.exception.ResourceNotFoundException;
import com.ikem.drone_delivery.repository.BatteryHistoryRepository;
import com.ikem.drone_delivery.service.impl.BatteryHistoryServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class BatteryHistoryServiceTest {

    /*@TestConfiguration
    static class BatteryHistoryServiceImplTestContextConfiguration {
        @Bean("batteryHistoryServiceBean")
        public BatteryHistoryService batteryHistoryService(){
            return new BatteryHistoryServiceImpl();
        }

        @Bean("modelMapperBean")
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }
    }
*/
    @MockBean
    private BatteryHistoryRepository batteryHistoryRepository;

    @Autowired
    private BatteryHistoryService batteryHistoryService;

    @Autowired
    private ModelMapper mapper;


   /* @Before
    public void setUp() {
        DroneDto drone = DroneDto.builder()
                .serialNo("ABC003")
                .build();

        BatteryHistory batteryHistory = new BatteryHistory(90, mapper.map(drone, Drone.class));
        when(batteryHistoryRepository.findByDrone_SerialNo(drone.getSerialNo()))
                .thenReturn(List.of(batteryHistory));
    }
*/
    @Test
    public void getBatteryHistoriesForDrone() {
        //BatteryHistory batteryHistory = new BatteryHistory(90, mapper.map(drone, Drone.class));

        DroneDto drone = DroneDto.builder()
                .serialNo("ABC003")
                .build();

        BatteryHistory batteryHistory = new BatteryHistory(90, mapper.map(drone, Drone.class));
        when(batteryHistoryRepository.findByDrone_SerialNo(drone.getSerialNo()))
                .thenReturn(List.of(batteryHistory));

        List<BatteryHistoryDto> result = batteryHistoryService.getBatteryHistoriesForDrone("ABC003");

        assertThat(1).isEqualTo(result.size());
    }

    @Test
    void throw_when_drone_is_not_found(){
        given(batteryHistoryRepository.findByDrone_SerialNo("ABC003"))
                .willReturn(null);

        assertThatThrownBy(() -> batteryHistoryService.getBatteryHistoriesForDrone("ABC003"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Battery history for drone not found with Serial No : 'ABC003'");
    }
}