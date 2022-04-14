package com.springapp.supermarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer left")
public class CustomerLeftException extends RuntimeException {
}
