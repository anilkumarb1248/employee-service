package com.app.bak.global.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.bak.exceptions.DuplicateUserException;
import com.app.bak.exceptions.UserNotFoundException;
import com.app.bak.util.ErrorResponse;

@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(value = { DuplicateUserException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<ErrorResponse> handleDuplicateUserException(DuplicateUserException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(String.valueOf(HttpStatus.CONFLICT.value()));
		errorResponse.setErrorMessage(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}

	@ExceptionHandler(value = { UserNotFoundException.class })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(String.valueOf(HttpStatus.NO_CONTENT.value()));
		errorResponse.setErrorMessage(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}

}
