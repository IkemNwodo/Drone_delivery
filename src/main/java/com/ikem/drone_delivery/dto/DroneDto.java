package com.ikem.drone_delivery.dto;

import com.ikem.drone_delivery.util.DroneModel;
import com.ikem.drone_delivery.util.DroneState;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class DroneDto {

    @NotEmpty
    @Size(max = 100, message = "Serial number must be 100 characters max.")
    private String serialNo;

    private DroneModel model;

    @NotEmpty
    @Max(value = 500, message = "Weight must not exceed 500gr.")
    @Min(value = 0, message = "Weight must be more than zero.")
    private Double weightLimit;

    private Double batteryCapacity;

    private DroneState state;

    private List<MedicationDto> medications;
}
