package com.kimleysoft.exception;

/**
 * @author xiang
 * @createDate 2018年10月19日 上午11:00:47
 */
public enum ExceptionEnum {

	SYSTEM_ERROR(500, "系统异常"),
	JOBNO_NOT_FOUND(404, "工单号不存在"),
	ILLEGAL_ACCESS(401, "非法访问");

	private int errorCode;
	private String errorMessage;
	
	private ExceptionEnum(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
