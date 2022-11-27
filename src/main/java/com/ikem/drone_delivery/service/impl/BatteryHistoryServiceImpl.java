package com.ikem.drone_delivery.service.impl;

import com.ikem.drone_delivery.dto.BatteryHistoryDto;
import com.ikem.drone_delivery.entity.BatteryHistory;
import com.ikem.drone_delivery.exception.ResourceNotFoundException;
import com.ikem.drone_delivery.repository.BatteryHistoryRepository;
import com.ikem.drone_delivery.service.BatteryHistoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BatteryHistoryServiceImpl implements BatteryHistoryService {

    @Autowired
    private BatteryHistoryRepository batteryHistoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<BatteryHistoryDto> getBatteryHistoriesForDrone(String droneSerialNo) {
        List<BatteryHistoryDto> batteryHistories;
        try {
            batteryHistories = batteryHistoryRepository.findByDrone_SerialNo(droneSerialNo)
                    .stream().map( batteryHistory ->
                    mapper.map(batteryHistory, BatteryHistoryDto.class)
            ).toList();

            log.info("BatteryHistoryServiceImpl: {}", batteryHistories);

        } catch (Exception e){
            throw new ResourceNotFoundException("Battery history for drone", "Serial No", droneSerialNo);
        }
        return batteryHistories;
    }
}
