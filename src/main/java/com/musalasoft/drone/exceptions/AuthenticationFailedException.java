package com.musalasoft.drone.exceptions;
public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String message){
        super(message);
    }
}
