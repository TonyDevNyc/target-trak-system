package com.target.trak.system.service.dto;

import java.util.List;

import com.target.trak.system.validations.TargetTrakValidationError;

public class TargetTrakApiResponse {

	private boolean success;
	private List<TargetTrakValidationError> errors;
	private String message;
	private int totalSize;

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

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}