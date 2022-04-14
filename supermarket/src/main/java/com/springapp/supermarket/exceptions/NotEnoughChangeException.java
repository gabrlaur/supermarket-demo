package com.springapp.supermarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not enough coins in cash register!")
public class NotEnoughChangeException extends RuntimeException{
}