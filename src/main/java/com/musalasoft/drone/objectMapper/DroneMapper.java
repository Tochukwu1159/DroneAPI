package com.musalasoft.drone.objectMapper;

import com.musalasoft.drone.models.Drone;
import com.musalasoft.drone.payloads.requests.DroneDto;
import com.musalasoft.drone.payloads.responses.DroneResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DroneMapper {
    private final ModelMapper modelMapper;
    public Drone mapToDrone(DroneDto droneDto) {
        return modelMapper.map(droneDto, Drone.class);
    }

    public DroneResponse mapToDroneResponse(Drone drone) {
        return modelMapper.map(drone, DroneResponse.class);
    }
}
