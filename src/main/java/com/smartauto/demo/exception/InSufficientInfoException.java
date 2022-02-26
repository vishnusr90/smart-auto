package com.smartauto.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InSufficientInfoException extends RuntimeException {
    public InSufficientInfoException(String message) {
        super(message);
    }
}
