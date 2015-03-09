package com.target.trak.system.security.dto.credentials;

import java.util.List;

import com.target.trak.system.validations.TargetTrakValidationError;

public class ForgotPasswordApiResponse {

	private boolean success;
	private List<TargetTrakValidationError> errors;
	private String errorMessage;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<TargetTrakValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<TargetTrakValidationError> errors) {
		this.errors = errors;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
