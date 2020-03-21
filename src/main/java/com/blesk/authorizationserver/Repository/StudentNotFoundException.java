package com.blesk.authorizationserver.Repository;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String exception) {
        super(exception);
    }

}