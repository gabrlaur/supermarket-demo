package com.springapp.supermarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotAffordException extends RuntimeException {
    public CannotAffordException(String message) {
        super(message);
    }
}