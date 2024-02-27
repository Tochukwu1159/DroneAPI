package com.musalasoft.drone.exceptions;

public class DroneNotFoundException extends RuntimeException{

    public DroneNotFoundException(String message){
        super(message);
    }
}
