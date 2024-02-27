package com.musalasoft.drone.exceptions;

import lombok.Getter;

@Getter
public class DroneAlreadyRegisteredException extends RuntimeException {
    public DroneAlreadyRegisteredException(String message){
        super(message);
    }
}
