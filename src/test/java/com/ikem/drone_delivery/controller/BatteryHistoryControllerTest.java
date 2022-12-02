package com.ikem.drone_delivery.controller;

import com.ikem.drone_delivery.service.BatteryHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatteryHistoryController.class)
class BatteryHistoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BatteryHistoryService service;

    @Test
    void getBatteryHistoriesForDrone() throws Exception {
        mvc.perform(get("/api/v1/drones/batteryHistories/{droneSerialNo}", "ABC007")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}