package com.ikem.drone_delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "batteryHistories")
public class BatteryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer batteryCapacity;

    @ManyToOne
    @JoinColumn(name = "serialNo")
    private Drone drone;

    public BatteryHistory() {
    }

    public BatteryHistory(Integer batteryCapacity, Drone drone) {
        this.batteryCapacity = batteryCapacity;
        this.drone = drone;
    }
}
