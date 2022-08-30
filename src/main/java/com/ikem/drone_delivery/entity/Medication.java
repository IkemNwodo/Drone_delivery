package com.ikem.drone_delivery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "medications")
public class Medication {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String name;
    private Double weight;
    private Integer code;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "serial_no", nullable = false)
    private Drone drone;
}
