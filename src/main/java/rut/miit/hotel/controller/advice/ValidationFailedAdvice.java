package rut.miit.hotel.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rut.miit.hotel.exception.ValidationFailedException;

@RestControllerAdvice
public class ValidationFailedAdvice {
    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validationFailHandler(ValidationFailedException ex) {
        return ex.getMessage();
    }
}
