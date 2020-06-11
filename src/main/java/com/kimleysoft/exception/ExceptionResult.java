package com.kimleysoft.exception;

public class ExceptionResult {
	
	
	private int errorCode;
	private String message;
	private Long timestamp;
	
	public ExceptionResult(ExceptionEnum exceptionEnum) {
		this.errorCode = exceptionEnum.getErrorCode();
		this.message = exceptionEnum.getErrorMessage();
		this.timestamp = System.currentTimeMillis();
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
