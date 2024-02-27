package com.musalasoft.drone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatteryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal batteryLevel;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    public BatteryHistory(BigDecimal batteryLevel, Drone drone) {
        this.batteryLevel = batteryLevel;
        this.drone = drone;
    }
}