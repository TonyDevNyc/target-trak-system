package com.target.trak.system.security.validations;

public class SecurityValidationError {

	private String field;
	private String errorMessage;

	public SecurityValidationError() {
	}

	public SecurityValidationError(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
