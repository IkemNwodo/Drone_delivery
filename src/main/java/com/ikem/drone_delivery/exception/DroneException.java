package com.ikem.drone_delivery.exception;

import org.springframework.http.HttpStatus;

public class DroneException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String message;

    public DroneException(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public DroneException(HttpStatus httpStatus, String message, String message1){
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
