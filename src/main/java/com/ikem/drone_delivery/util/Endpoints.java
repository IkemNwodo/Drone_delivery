package com.ikem.drone_delivery.util;

public interface Endpoints {
    String DRONE_BASE_URL ="/api/v1/drones";
    String MEDICATION_BASE_URL = "/api/v1";
    String REGISTER_DRONE = "/register";
    String LOAD_DRONE_WITH_MEDICATIONS = "/load/{serialNo}";
    String AVAILABLE_DRONES = "/available";
    String GET_BATTERY_LEVEL = "/battery-level/{serialNo}";
    String GET_MEDICATIONS = "/drones/{droneSerialNo}/medications";

}
