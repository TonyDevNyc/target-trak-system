package com.target.trak.system.service.dto.common;

import java.util.List;

import com.target.trak.system.validations.TargetTrakValidationError;

public abstract class BaseTargetTrakApiResponse implements TargetTrakApiResponse {

	private boolean success;
	private List<TargetTrakValidationError> errors;
	private String message;
	private int totalSize;
	private TargetTrakErrorTypeEnum errorType;

	@Override
	public int getTotalSize() {
		return totalSize;
	}

	@Override
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

	@Override
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public List<TargetTrakValidationError> getErrors() {
		return errors;
	}

	@Override
	public void setErrors(List<TargetTrakValidationError> errors) {
		this.errors = errors;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public TargetTrakErrorTypeEnum getErrorType() {
		return errorType;
	}

	@Override
	public void setErrorType(TargetTrakErrorTypeEnum errorType) {
		this.errorType = errorType;
	}
}