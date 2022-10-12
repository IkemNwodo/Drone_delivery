package com.ikem.drone_delivery.util.converter;

import com.ikem.drone_delivery.util.DroneModel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DroneModelConverter implements AttributeConverter<DroneModel, String> {

    @Override
    public String convertToDatabaseColumn(DroneModel droneModel) {
        if (droneModel == null)
            return null;
        return droneModel.name();
    }

    @Override
    public DroneModel convertToEntityAttribute(String s) {
        return DroneModel.valueOf(s);
    }
}
