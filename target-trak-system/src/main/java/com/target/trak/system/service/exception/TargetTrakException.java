package com.target.trak.system.service.exception;

import com.target.trak.system.service.dto.common.TargetTrakApiResponse;

public class TargetTrakException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private TargetTrakApiResponse response;

	public TargetTrakException() {
		super();
	}

	public TargetTrakException(String msg) {
		super(msg);
	}

	public TargetTrakApiResponse getResponse() {
		return response;
	}

	public void setResponse(TargetTrakApiResponse response) {
		this.response = response;
	}

}
