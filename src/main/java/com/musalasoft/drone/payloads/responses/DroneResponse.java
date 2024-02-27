package com.musalasoft.drone.payloads.responses;

import com.musalasoft.drone.enums.Model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneResponse
{
    private String serialNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Model model;
    @NotNull
    private BigDecimal batteryLevel;
    @NotNull
    private double weightLimit;
}
