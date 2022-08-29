package com.ikem.drone_delivery.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

@Data
public class MedicationDto {

    @Pattern(regexp = "^[\\w\\-]*$", message = "Invalid medication name! This allows only letters, numbers, ‘-‘, ‘_’")
    private String name;

    @DecimalMax(value = "500.0", message = "Weight should be no more than 500g!")
    @DecimalMin(value = "0.0", inclusive = false, message = "Weight should be greater than 0g!")
    private Double weight;

    @Pattern(regexp = "^[A-Z_0-9]*$", message = "Invalid medication code! This allows only upper case letters, underscore and numbers")
    private String code;

    //    /site/images/test.png
    @Pattern(regexp = "(\\/.*?\\.\\w{3})", message = "Invalid image path!")
    private String imagePath;
}
