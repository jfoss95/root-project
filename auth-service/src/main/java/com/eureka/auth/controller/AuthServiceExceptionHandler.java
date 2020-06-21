package com.eureka.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eureka.auth.vo.ErrorVO;
import com.eureka.auth.exception.ServiceException;

@RestControllerAdvice
public class AuthServiceExceptionHandler {
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleException(Exception ex) {
        return new ErrorVO(Integer.toString(HttpStatus.CONFLICT.value()),"Something went wrong");
    }
    
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorVO handleUserNotFoundException(ServiceException ex) {
        return new ErrorVO(Integer.toString(HttpStatus.SERVICE_UNAVAILABLE.value()),"Service Unavailable");
    }
}
