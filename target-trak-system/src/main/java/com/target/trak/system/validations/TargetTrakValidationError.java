package com.target.trak.system.validations;

public class TargetTrakValidationError {

	private String fieldName;
	private String errorMessage;

	public TargetTrakValidationError() {
	}

	public TargetTrakValidationError(String fieldName, String errorMessage) {
		this.fieldName = fieldName;
		this.errorMessage = errorMessage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
