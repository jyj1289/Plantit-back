package com.eugene.diary.shared.error;

import com.eugene.diary.shared.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBindException(org.springframework.validation.BindException e) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : e.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(new ErrorResponse(GlobalErrorProperty.BAD_REQUEST, errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(new ErrorResponse(GlobalErrorProperty.BAD_REQUEST, errors));
    }

    @ExceptionHandler(MissingRequestValueException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMissingRequestValueException(MissingRequestValueException e) {

        return ResponseEntity
                .status(GlobalErrorProperty.BAD_REQUEST.getStatus())
                .body(new ErrorResponse(GlobalErrorProperty.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(DiaryException.class)
    public ResponseEntity<ErrorResponse> handleSinabroException(DiaryException e) {
        return ResponseEntity
                .status(e.getErrorProperty().getStatus())
                .body(new ErrorResponse(e.getErrorProperty()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(GlobalErrorProperty.INTERNAL_SERVER_ERROR.getStatus())
                .body(new ErrorResponse(GlobalErrorProperty.INTERNAL_SERVER_ERROR));
    }

}
