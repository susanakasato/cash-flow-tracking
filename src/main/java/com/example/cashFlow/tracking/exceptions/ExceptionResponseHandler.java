package com.example.cashFlow.tracking.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionResponseHandler {
	
	private ExceptionResponse getExceptionResponse(RuntimeException e, HttpServletRequest req, HttpStatus status) {
		return new ExceptionResponse(e.getMessage(), status, req.getRequestURI(), Instant.now());
	}

	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<ExceptionResponse> dataValiation(DataValidationException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(getExceptionResponse(e, req, status));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> database(ResourceNotFoundException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(getExceptionResponse(e, req, status));
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ExceptionResponse> auth(UsernameNotFoundException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(getExceptionResponse(e, req, status));
	}
}
