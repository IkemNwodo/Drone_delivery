package com.ikem.drone_delivery.repository;

import com.ikem.drone_delivery.entity.Drone;
import com.ikem.drone_delivery.util.DroneState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    Optional<Drone> findBySerialNo(String serialNo);

    Page<Drone> findDronesByStateEquals(DroneState droneState, Pageable pageable);
    Boolean existsBySerialNo(String serialNo);
}
