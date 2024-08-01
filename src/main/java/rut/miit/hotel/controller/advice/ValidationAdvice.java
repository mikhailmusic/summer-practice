package rut.miit.hotel.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rut.miit.hotel.exception.CheckFailedException;

@RestControllerAdvice
public class ValidationAdvice {
    @ExceptionHandler(CheckFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validationFailHandler(CheckFailedException ex) {
        return ex.getMessage();
    }
}
