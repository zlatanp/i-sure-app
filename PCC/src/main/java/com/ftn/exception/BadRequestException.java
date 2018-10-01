package com.ftn.exception;

/**
 * Created by Jasmina on 10/12/2017.
 */
public class BadRequestException extends RuntimeException{

    public BadRequestException() {}

    public BadRequestException(String message) {
        super(message);
    }
}
