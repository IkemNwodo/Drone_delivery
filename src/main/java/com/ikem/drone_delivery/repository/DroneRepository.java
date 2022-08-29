package com.ikem.drone_delivery.repository;

import com.ikem.drone_delivery.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    Optional<Drone> findBySerialNo(String serialNo);
    Boolean existsBySerialNo(String serialNo);
}
