package com.kimleysoft.exception;

/**
 * API Exception
 *
 * @author xiang
 * @version 1.0.0
 * @date 2018/11/22 19:59
 */
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ExceptionEnum exceptionEnum;

	public APIException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getErrorMessage());
		this.exceptionEnum = exceptionEnum;
	}

	public ExceptionEnum getExceptionEnum() {
		return exceptionEnum;
	}

	public void setExceptionEnum(ExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}
	
}
