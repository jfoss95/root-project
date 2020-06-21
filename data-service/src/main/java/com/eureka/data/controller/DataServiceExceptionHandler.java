package com.eureka.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eureka.data.exception.EmailExistsException;
import com.eureka.data.exception.IncorrectFormatException;
import com.eureka.data.exception.RoleExistsException;
import com.eureka.data.exception.RoleNotFoundException;
import com.eureka.data.exception.UserNotFoundException;
import com.eureka.data.exception.UsernameExistsException;
import com.eureka.data.vo.ErrorVO;

@RestControllerAdvice
public class DataServiceExceptionHandler {
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleException(Exception ex) {
        return new ErrorVO(Integer.toString(HttpStatus.CONFLICT.value()),"Something went wrong");
    }
    
    @ExceptionHandler(IncorrectFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorVO handleIncorrectFormatException(IncorrectFormatException ex) {
        return new ErrorVO(Integer.toString(HttpStatus.BAD_REQUEST.value()),"Incorrect format");
    }
    
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleUsernameExistsException(UsernameExistsException ex) {
        return new ErrorVO(Integer.toString(HttpStatus.CONFLICT.value()),"Username already exists");
    }
    
    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleEmailExistsException(EmailExistsException ex) {
        return new ErrorVO(Integer.toString(HttpStatus.CONFLICT.value()),"Email already exists");
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorVO handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorVO(Integer.toString(HttpStatus.NOT_FOUND.value()),"User not found");
    }
    
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorVO handleRoleNotFoundException(RoleNotFoundException ex) {
        return new ErrorVO(Integer.toString(HttpStatus.NOT_FOUND.value()),"Role not found");
    }
    
    @ExceptionHandler(RoleExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorVO handleRoleExistsException(RoleExistsException ex) {
        return new ErrorVO(Integer.toString(HttpStatus.CONFLICT.value()),"All roles already exist");
    }
}
