package com.example.security.errors;

public class PersonNotCreatedException extends RuntimeException {

    public PersonNotCreatedException(String message) {
        super(message);
    }
}
