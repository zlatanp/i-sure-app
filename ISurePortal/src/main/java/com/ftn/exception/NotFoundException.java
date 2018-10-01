package com.ftn.exception;

/**
 * Created by Jasmina on 16/11/2017.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
