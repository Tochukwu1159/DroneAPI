package com.musalasoft.drone.exceptions;
public class CustomInternalServerException extends RuntimeException{
    public CustomInternalServerException(String message){
        super(message);
    }
}