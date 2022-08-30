package com.ikem.drone_delivery.repository;

import com.ikem.drone_delivery.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByDrone_SerialNo(String droneSerialNo);
}
