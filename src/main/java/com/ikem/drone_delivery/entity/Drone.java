package com.ikem.drone_delivery.entity;

import com.ikem.drone_delivery.util.DroneModel;
import com.ikem.drone_delivery.util.DroneState;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "drones")
public class Drone implements Serializable {

    @Id
    @Column(name = "serialNo")
    private String serialNo;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private Double weightLimit;
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany(mappedBy = "drone",cascade = CascadeType.ALL)
    private Set<Medication> medications = new HashSet<>();

}
