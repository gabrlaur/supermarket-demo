package com.springapp.supermarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product is out of Stock")
public class SoldOutException extends RuntimeException {
}