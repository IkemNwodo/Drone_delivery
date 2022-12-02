package com.ikem.drone_delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikem.drone_delivery.dto.DroneDto;
import com.ikem.drone_delivery.dto.MedicationDto;
import com.ikem.drone_delivery.service.DroneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DroneController.class)
class DroneControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DroneService service;


    @Test
    void registerDrone() throws Exception {
        String serialNo = "ABC007";

        DroneDto droneDto = DroneDto.builder()
                .serialNo(serialNo)
                .weightLimit(100.0)
                .build();

        mvc.perform(post("/api/v1/drones/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(droneDto)))
                        .andExpect(status().isCreated());
    }

    @Test
    void loadDroneWithMedication() throws Exception {
        List<MedicationDto> medicationDtos = List.of(MedicationDto.builder()
                .weight(50.0)
                .build());

        mvc.perform(post("/api/v1/drones/load/{serialNo}", "ABC007")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medicationDtos)))
                .andExpect(status().isOk());
    }

    @Test
    void getAvailableDrones() throws Exception {
        mvc.perform(get("/api/v1/drones/available")
                        .contentType("application/json")
                        .param("pageNo", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllDrones() throws Exception {
        mvc.perform(get("/api/v1/drones")
                        .contentType("application/json")
                        .param("pageNo", String.valueOf(0))
                        .param("pageSize", String.valueOf(10)))
                .andExpect(status().isOk());
    }

    @Test
    void getBatteryLevel() throws Exception {
        mvc.perform(get("/api/v1/drones/battery-level/{serialNo}", "ABC007")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}