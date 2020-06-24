package com.eureka.common.exception;

import org.springframework.http.HttpStatus;

import com.eureka.common.constants.ErrorCodeConstants;

public class IncorrectFormatException extends Exception {
	private static final long serialVersionUID = -1855566715271304408L;
	
	public static final HttpStatus RESPONSE_STATUS = HttpStatus.BAD_REQUEST;
	public static final String ERROR_CODE = ErrorCodeConstants.INCORRECT_FORMAT;
	public static final String MESSAGE = "Incorrect format";
}
