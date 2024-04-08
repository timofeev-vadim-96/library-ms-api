package ru.gb.exceptions;

public class TheBookIsBusy extends RuntimeException{
    public TheBookIsBusy(String message) {
        super(message);
    }
}
