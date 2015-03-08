package com.target.trak.system.service.dto;

import java.util.List;

public class TargetTrakApiResponse {

	private boolean success;
	private List<TargetTrakError> errors;
	private int totalSize;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<TargetTrakError> getErrors() {
		return errors;
	}

	public void setErrors(List<TargetTrakError> errors) {
		this.errors = errors;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
