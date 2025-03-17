package com.example.cashFlow.tracking.exceptions;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private HttpStatus status;
	private String path;
	private Instant timestamp;
	
	public ExceptionResponse() {}
	
	public ExceptionResponse(String message, HttpStatus status, String path, Instant timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.path = path;
		this.timestamp = timestamp;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status.toString();
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

}
