package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	public ResponseEntity<ExceptionMessage> handleException(Exception exception){
		String message = exception.getMessage();
		if (message == null || message.contains("is null")) {
			message = ExceptionConstants.NOT_FOUND;
		}
		else if (message.contains("entity with id")) {
			message = ExceptionConstants.INVALID_ID;
		}
		else if (message.contains("must not be null") || message.contains("must not be blank")) {
			message = ExceptionConstants.NOT_EMPTY_VIOLATION;
		}
		else if (message.contains("ConstraintViolationException")) {
			message = ExceptionConstants.UNIQUE_VIOLATION;
		}
		else if (message.contains("must be a well-formed email address")) {
			message = ExceptionConstants.INVALID_EMAIL;
		}
		return new ResponseEntity<>(new ExceptionMessage(message, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
