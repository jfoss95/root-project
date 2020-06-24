package com.eureka.auth.vo;


import java.util.ArrayList;
import java.util.List;

public class ErrorsVO {
	private final String errorCode;
	private final String message;
	private final List<ErrorVO> errors;

	public ErrorsVO(final String reason, final String errorCode, final String message) {
		this.errorCode = errorCode;
		this.message = message;
        List<ErrorVO> errorsList = new ArrayList<>();
        errorsList.add((new ErrorVO(reason, errorCode, message)));
        this.errors = errorsList;
    }

    private ErrorsVO(final String errorCode, final String message, final List<ErrorVO> errors) {
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public static ErrorsVO copyWithMessage(final ErrorsVO errorsVO, final String message) {
        return new ErrorsVO(errorsVO.errorCode, message, errorsVO.errors);
    }

    public String getErrorCode() {
        return errorCode;
    }
    
    public String getMessage() {
        return message;
    }

    public List<ErrorVO> getErrors() {
        return errors;
    }
}
