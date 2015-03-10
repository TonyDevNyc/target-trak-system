package com.target.trak.system.validations.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;

@Component("referenceDataValidator")
public class ReferenceDataValidatorImpl implements TargetTrakValidator<ReferenceDataApiRequest> {

	@Override
	public List<TargetTrakValidationError> validate(final ReferenceDataApiRequest request) throws TargetTrakValidationException {
		List<TargetTrakValidationError> validationErrors = new ArrayList<TargetTrakValidationError>();
		
		return validationErrors;
	}

}
