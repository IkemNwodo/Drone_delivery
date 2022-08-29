package com.ikem.drone_delivery.util;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


public enum DroneModel {
    LW("Lightweight"),
    MW("Middleweight"),
    CW("Cruiserweight"),
    HW("Heavyweight");

    final String value;

    DroneModel(String value){
        this.value = value;
    }
}
