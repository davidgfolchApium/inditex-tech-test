package com.inditex.techtest.controller.base;

import com.inditex.techtest.service.exception.ProductPriceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ProductPriceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), ex.getMessage()), NOT_FOUND);
    }

}
