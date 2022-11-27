package com.ikem.drone_delivery.dto;

import com.ikem.drone_delivery.util.DroneModel;
import com.ikem.drone_delivery.util.DroneState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
public class DroneDto {

    @NotEmpty
    @Size(max = 100, message = "Serial number must be 100 characters max.")
    private String serialNo;

    private DroneModel model;

    private Double weightLimit;

    private Double batteryCapacity;

    private DroneState state;

    private List<MedicationDto> medications;
}
