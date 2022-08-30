package com.ikem.drone_delivery.service.impl;

import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.entity.Medication;
import com.ikem.drone_delivery.repository.MedicationRepository;
import com.ikem.drone_delivery.service.MedicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private ModelMapper mapper;
    private MedicationRepository medicationRepository;

    @Override
    public List<MedicationDto> getMedicationsForDrone(String droneSerialNo) {

        List<Medication> medications = medicationRepository.findByDrone_SerialNo(droneSerialNo);

        return medications.stream().map(this::mapToDTO).toList();
    }

    private MedicationDto mapToDTO(Medication medication){
        return mapper.map(medication, MedicationDto.class);
    }

}
