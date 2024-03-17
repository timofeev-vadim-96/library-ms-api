package com.example.restapi.exceptions;

public class TheBookIsBusy extends RuntimeException{
    public TheBookIsBusy(String message) {
        super(message);
    }
}
