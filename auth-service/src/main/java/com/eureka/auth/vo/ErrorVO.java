package com.eureka.auth.vo;

public class ErrorVO {
    private String reason;
    private String errorCode;
    private String message;
    
    public ErrorVO() {
    }

    public ErrorVO(String reason, String errorCode, String message) {
    	this();
        this.errorCode = errorCode;
		this.reason = reason;
        this.message = message;
    }

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }
}
