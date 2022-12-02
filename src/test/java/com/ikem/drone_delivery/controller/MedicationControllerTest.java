package com.ikem.drone_delivery.controller;

import com.ikem.drone_delivery.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicationController.class)
class MedicationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicationService service;

    @Test
    void getMedications() throws Exception {
        mvc.perform(get("/api/v1/drones/{droneSerialNo}/medications", "ABC007")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}