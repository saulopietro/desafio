package com.desafio.desafio.controller.handlers;

import com.desafio.desafio.dto.CustomError;
import com.desafio.desafio.service.exceptions.CustomerNotFoundException;
import com.desafio.desafio.service.exceptions.SellerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class CustomerNotFound {


    @ExceptionHandler(CustomerNotFoundException.class)
    private ResponseEntity<CustomError> customerNotFound(CustomerNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
