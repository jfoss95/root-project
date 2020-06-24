package com.eureka.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eureka.common.constants.ErrorCodeConstants;
import com.eureka.common.exception.EmailExistsException;
import com.eureka.common.exception.IncorrectFormatException;
import com.eureka.common.exception.UserNotFoundException;
import com.eureka.common.exception.UsernameExistsException;
import com.eureka.data.vo.ErrorVO;

@RestControllerAdvice
public class DataServiceExceptionHandler {
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleException(Exception ex) {
        return new ErrorVO(Integer.toString(HttpStatus.CONFLICT.value()), 
        		ErrorCodeConstants.UNEXPECTED_ERROR,
        		"Something went wrong");
    }
    
    @ExceptionHandler(IncorrectFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorVO handleIncorrectFormatException(IncorrectFormatException ex) {
        return new ErrorVO(Integer.toString(IncorrectFormatException.RESPONSE_STATUS.value()),
        		IncorrectFormatException.ERROR_CODE,
        		IncorrectFormatException.MESSAGE);
    }
    
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleUsernameExistsException(UsernameExistsException ex) {
        return new ErrorVO(Integer.toString(UsernameExistsException.RESPONSE_STATUS.value()),
        		UsernameExistsException.ERROR_CODE,
        		UsernameExistsException.MESSAGE);
    }
    
    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleEmailExistsException(EmailExistsException ex) {
        return new ErrorVO(Integer.toString(EmailExistsException.RESPONSE_STATUS.value()),
        		EmailExistsException.ERROR_CODE,
        		EmailExistsException.MESSAGE);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorVO handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorVO(Integer.toString(UserNotFoundException.RESPONSE_STATUS.value()),
        		UserNotFoundException.ERROR_CODE,
        		UserNotFoundException.MESSAGE);
    }
}
