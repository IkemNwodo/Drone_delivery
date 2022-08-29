package com.ikem.drone_delivery.entity;

import com.ikem.drone_delivery.util.DroneModel;
import com.ikem.drone_delivery.util.DroneState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "drones")
public class Drone {

    @Id
    private String serialNo;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private Double weightLimit;
    private Double batteryCapacity;
    @Enumerated(EnumType.ORDINAL)
    private DroneState state;

    @OneToMany(mappedBy = "drone",cascade = CascadeType.ALL)
    private List<Medication> medications = new ArrayList<>();

}