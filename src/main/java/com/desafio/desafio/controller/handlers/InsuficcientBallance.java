package com.desafio.desafio.controller.handlers;

import com.desafio.desafio.dto.CustomError;
import com.desafio.desafio.service.exceptions.InsufificientBalanceException;
import com.desafio.desafio.service.exceptions.SellerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class InsuficcientBallance {


    @ExceptionHandler(InsufificientBalanceException.class)
    private ResponseEntity<CustomError> insufficientBalance(InsufificientBalanceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INSUFFICIENT_STORAGE;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
