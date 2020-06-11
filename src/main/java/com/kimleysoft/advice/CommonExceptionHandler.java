package com.kimleysoft.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kimleysoft.exception.APIException;
import com.kimleysoft.exception.ExceptionEnum;
import com.kimleysoft.exception.ExceptionResult;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResult> handleException(Exception e){
		APIException apiException;
		if(e instanceof APIException) {
			apiException = (APIException) e;
		}else {
			e.printStackTrace();
			apiException = new APIException(ExceptionEnum.SYSTEM_ERROR);
		}
		ExceptionEnum exceptionEnum = apiException.getExceptionEnum();
		return ResponseEntity.status(exceptionEnum.getErrorCode()).body(new ExceptionResult(exceptionEnum));
	}
}
