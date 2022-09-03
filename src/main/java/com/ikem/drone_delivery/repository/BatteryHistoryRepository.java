package com.ikem.drone_delivery.repository;

import com.ikem.drone_delivery.entity.BatteryHistory;
import com.ikem.drone_delivery.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryHistoryRepository extends JpaRepository<BatteryHistory, Long> {

    List<BatteryHistory> findByDrone_SerialNo(String droneSerialNo);

}
