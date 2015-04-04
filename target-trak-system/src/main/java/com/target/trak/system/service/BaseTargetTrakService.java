package com.target.trak.system.service;

import java.util.List;

import com.target.trak.system.service.dto.common.TargetTrakApiResponse;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;

public abstract class BaseTargetTrakService {

	public final TargetTrakException generateServiceException(
			final TargetTrakApiResponse response,
			final List<TargetTrakValidationError> validationErrors, 
			final TargetTrakErrorTypeEnum errorType, 
			final String message) {
		TargetTrakException exception = new TargetTrakException(message);
		response.setSuccess(Boolean.FALSE);
		response.setErrorType(errorType);
		response.setErrors(validationErrors);
		response.setMessage(message);
		exception.setResponse(response);
		return exception;
	}
}
