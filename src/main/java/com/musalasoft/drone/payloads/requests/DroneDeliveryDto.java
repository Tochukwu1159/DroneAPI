package com.musalasoft.drone.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DroneDeliveryDto {
    private String serialNumber;
    private String source;
    private String destination;
}

