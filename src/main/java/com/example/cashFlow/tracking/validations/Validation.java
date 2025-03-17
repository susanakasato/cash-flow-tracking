package com.example.cashFlow.tracking.validations;

import java.util.Optional;

import jakarta.validation.Validator;

public class Validation {

	private static Validator validator = jakarta.validation.Validation.buildDefaultValidatorFactory().getValidator();
	
	public static Optional<String> getValidationExceptionMessage(Object object, Class<?>... groups) {
		return validator.validate(object, groups).stream().map(violation -> violation.getMessage()).reduce((a, b) -> a + " " + b);
	}
}
