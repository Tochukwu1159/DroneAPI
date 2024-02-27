package com.musalasoft.drone.exceptions;

public class CustomOutOfStock extends RuntimeException{
    public CustomOutOfStock(String message){
        super(message);
    }
}
