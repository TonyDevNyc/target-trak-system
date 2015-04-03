package com.target.trak.system.service;

import java.util.List;

import com.target.trak.system.service.dto.TargetTrakApiResponse;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;

public class BaseTargetTrakService {

	public final TargetTrakException generateServiceException(final List<TargetTrakValidationError> validationErrors, final TargetTrakErrorTypeEnum errorType, final String message) {
		TargetTrakException exception = new TargetTrakException(message);
		TargetTrakApiResponse response = new TargetTrakApiResponse();
		response.setSuccess(Boolean.FALSE);
		response.setErrorType(errorType);
		response.setErrors(validationErrors);
		response.setMessage(message);
		exception.setResponse(response);
		return exception;
	}
}
