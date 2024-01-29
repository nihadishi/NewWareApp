package com.example.warehouseapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    ExceptionDto handle(NotFoundException exception) {
        log.info("ActionLog.handle.error NotFoundException handled");
        return new ExceptionDto(exception.getMessage(),HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(CustomerException.class)
    ExceptionDto handle(CustomerException exception) {
        log.info("ActionLog.handle.error CustomerException handled");
        return new ExceptionDto(exception.getMessage(),HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(AuthenticationException.class)
    ExceptionDto handle(AuthenticationException exception) {
        log.info("ActionLog.handle.error AuthenticationException handled");
        return new ExceptionDto(exception.getMessage(),HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<ExceptionDto> handle(MethodArgumentNotValidException exception){
        List<ExceptionDto> errors= new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(e->errors.add(new ExceptionDto(e.getDefaultMessage(),HttpStatus.BAD_REQUEST.value())));
        return  errors;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ExceptionDto paramsValidationHandler(ConstraintViolationException exception) {
        return new ExceptionDto(exception.getMessage(),HttpStatus.BAD_REQUEST.value());
    }
}