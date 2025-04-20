package com.example.liquorhub.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public record LiquorHubException(String message, HttpStatus status) {
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<LiquorHubException> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new LiquorHubException(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<LiquorHubException> handleNullPointerException(NullPointerException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LiquorHubException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<LiquorHubException> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new LiquorHubException(e.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<LiquorHubException> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new LiquorHubException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<LiquorHubException> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new LiquorHubException(e.getMessage(), HttpStatus.NOT_FOUND));
    }
}
