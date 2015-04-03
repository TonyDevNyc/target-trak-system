package com.target.trak.system.validations.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;

@Component("companyValidator")
public class CompanyValidatorImpl implements TargetTrakValidator<CompanyApiRequest> {

	@Override
	public List<TargetTrakValidationError> validate(final CompanyApiRequest request) throws TargetTrakValidationException {
		List<TargetTrakValidationError> validationErrors = new ArrayList<TargetTrakValidationError>();
		
		if (request == null) {
			throw new TargetTrakValidationException("API request is null");
		}

		final CompanyDto companyDto = request.getCompany();
		if (companyDto == null) {
			throw new TargetTrakValidationException("Company is null");
		}
		
		return validationErrors;
	}

}
