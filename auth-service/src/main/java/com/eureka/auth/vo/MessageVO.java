package com.eureka.auth.vo;

public class MessageVO {
	String message;

	public MessageVO() {
	}
	
	public MessageVO(String message) {
		this();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
