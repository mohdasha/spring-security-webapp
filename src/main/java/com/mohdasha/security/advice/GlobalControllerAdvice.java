package com.mohdasha.security.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mohdasha.security.exception.UserAccountException;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	@ExceptionHandler(value = UserAccountException.class)
	public ResponseEntity<?> handleException(UserAccountException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ex.getMessage());
	}
}
