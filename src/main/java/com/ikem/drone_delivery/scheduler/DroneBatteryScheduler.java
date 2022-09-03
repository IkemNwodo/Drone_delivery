package com.ikem.drone_delivery.scheduler;

import com.ikem.drone_delivery.entity.BatteryHistory;
import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.exception.DroneException;
import com.ikem.drone_delivery.repository.BatteryHistoryRepository;
import com.ikem.drone_delivery.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
@Slf4j
public class DroneBatteryScheduler {

    private DroneRepository droneRepository;
    private BatteryHistoryRepository batteryHistoryRepository;


    public DroneBatteryScheduler(DroneRepository droneRepository, BatteryHistoryRepository batteryHistoryRepository) {
        this.droneRepository = droneRepository;
        this.batteryHistoryRepository = batteryHistoryRepository;
    }

    @Scheduled(fixedRate = 5, initialDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void batteryHistory() {
        List<Drone> drones = droneRepository.findAll();
        log.info("Drones: {}", drones);

        try {
            List<BatteryHistory> batteryHistories = drones.stream()
                    .map(drone -> new BatteryHistory(
                            drone.getBatteryCapacity(),
                            drone)
                    )
                    .toList();
            batteryHistoryRepository.saveAll(batteryHistories);
            log.info("Battery history: {}", batteryHistories);
        } catch (Exception e) {
            throw new DroneException(HttpStatus.EXPECTATION_FAILED, "Exception is occurred while running the Battery scheduler: " + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 5, initialDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void batteryLevelUpdate() {

        List<Drone> drones = droneRepository.findAll();

        try {
            drones.forEach(drone ->
                    drone.setBatteryCapacity(ThreadLocalRandom.current().nextInt(100 + 1))
            );
            log.info("Battery update: {}", drones);
            droneRepository.saveAll(drones);
        } catch (Exception e) {
            throw new DroneException(HttpStatus.EXPECTATION_FAILED, "Exception is occurred while running the Battery scheduler");
        }

    }
}
