package com.springapp.supermarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid coins were given!")
public class PayNotAcceptedException extends RuntimeException {
}