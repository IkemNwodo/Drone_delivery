package com.ikem.drone_delivery.repository;

import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.util.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    Optional<Drone> findBySerialNo(String serialNo);

    List<Drone> findDronesByStateEquals(DroneState droneState);
    Boolean existsBySerialNo(String serialNo);
}
