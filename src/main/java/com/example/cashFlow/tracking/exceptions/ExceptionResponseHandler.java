package com.example.cashFlow.tracking.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionResponseHandler {
	
	private ExceptionResponse getExpcetionResponse(RuntimeException e, HttpServletRequest req, HttpStatus status) {
		return new ExceptionResponse(e.getMessage(), status, req.getRequestURI(), Instant.now());
	}

	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<ExceptionResponse> dataValiation(DataValidationException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(status).body(getExpcetionResponse(e, req, status));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> database(ResourceNotFoundException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return ResponseEntity.status(status).body(getExpcetionResponse(e, req, status));
	}
}
