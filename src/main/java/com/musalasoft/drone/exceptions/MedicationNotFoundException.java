package com.musalasoft.drone.exceptions;

public class MedicationNotFoundException extends RuntimeException{

    public MedicationNotFoundException(String message){
        super(message);
    }
}
