package com.musalasoft.drone.exceptions;

public class MedicineDetailsAlreadyExistException extends RuntimeException {
    public MedicineDetailsAlreadyExistException(String message){
        super (message);
    }
}
