package com.target.trak.system.service;

import java.util.List;

import com.target.trak.system.service.dto.common.TargetTrakApiRequest;
import com.target.trak.system.service.dto.common.TargetTrakApiResponse;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;

public interface TargetTrakService<T extends TargetTrakApiRequest, S extends TargetTrakApiResponse> {

	public S processRequest(final T request) throws TargetTrakException;
	
	public List<TargetTrakValidationError> validateRequest(final T request) throws TargetTrakException;
}
