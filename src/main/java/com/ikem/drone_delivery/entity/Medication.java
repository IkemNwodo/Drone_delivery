package com.ikem.drone_delivery.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medId;

    private String name;
    private Double weight;
    private Integer code;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "serial_no")
    private Drone drone;
}
