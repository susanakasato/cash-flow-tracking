package com.example.cashFlow.tracking.exceptions;

public class DataValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataValidationException(String message) {
		super(message);
	}
}
