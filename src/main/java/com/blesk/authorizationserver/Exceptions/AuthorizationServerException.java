package com.blesk.authorizationserver.Exceptions;

public class AuthorizationServerException extends RuntimeException{

    public AuthorizationServerException(String message)
    {
        super(message);
    }
}