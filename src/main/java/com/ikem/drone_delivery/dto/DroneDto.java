package com.ikem.drone_delivery.dto;

import com.ikem.drone_delivery.util.DroneModel;
import com.ikem.drone_delivery.util.DroneState;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {

    @NotEmpty
    @Size(max = 100, message = "Serial number must be 100 characters max.")
    private String serialNo;

    private DroneModel model;

    private Double weightLimit;

    private Double batteryCapacity;

    private DroneState state;

    private Set<MedicationDto> medications;
}
