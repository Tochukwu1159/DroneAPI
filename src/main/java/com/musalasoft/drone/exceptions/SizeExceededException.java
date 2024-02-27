package com.musalasoft.drone.exceptions;


public class SizeExceededException extends RuntimeException{
    public SizeExceededException(String message){
        super(message);
    }
}