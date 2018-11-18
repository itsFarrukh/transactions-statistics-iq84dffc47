package com.n26.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.n26.exception.UnprocessableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnprocessableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handleUnProceessedEntityException(){
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
   // @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleExceptionforInvalidFormat() {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
