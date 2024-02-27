package com.musalasoft.drone.exceptions;

public class UserPasswordMismatchException extends RuntimeException{
    public UserPasswordMismatchException(String message){
        super(message);
    }
}
