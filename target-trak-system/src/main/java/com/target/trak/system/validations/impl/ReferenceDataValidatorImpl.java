package com.target.trak.system.validations.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.rules.ReferenceDataRules;

@Component("referenceDataValidator")
public class ReferenceDataValidatorImpl implements TargetTrakValidator<ReferenceDataApiRequest> {

	@Qualifier("referenceDataRules")
	@Autowired
	private ReferenceDataRules referenceDataRules;

	@Override
	public List<TargetTrakValidationError> validate(final ReferenceDataApiRequest request) throws TargetTrakValidationException {
		List<TargetTrakValidationError> validationErrors = new ArrayList<TargetTrakValidationError>();

		if (request == null) {
			throw new TargetTrakValidationException("API request is null");
		}

		final ReferenceDataDto referenceDataDto = request.getReferenceDataDto();
		if (referenceDataDto == null) {
			throw new TargetTrakValidationException("Reference Data is null");
		}

		String type = referenceDataDto.getType();
		String label = referenceDataDto.getLabel();
		String value = referenceDataDto.getValue();

		validateType(validationErrors, type);
		validateLabel(validationErrors, label);
		validateValue(validationErrors, value);
		
		if (!StringUtils.isEmpty(type) && !StringUtils.isEmpty(label) && !StringUtils.isEmpty(value)) {
			validationErrors.add(referenceDataRules.referenceDataAlreadyExists(type, label, value));
		}
		validationErrors.removeAll(Collections.singleton(null));
		return validationErrors;
	}

	private void validateType(final List<TargetTrakValidationError> validationErrors, final String type) {
		TargetTrakValidationError typeError = referenceDataRules.isTypeEmpty(type);
		if (typeError == null) {
			validationErrors.add(referenceDataRules.isTypeValidLength(type));
			validationErrors.add(referenceDataRules.typeContainsAllowableChars(type));
		} else {
			validationErrors.add(typeError);
		}
	}

	private void validateLabel(final List<TargetTrakValidationError> validationErrors, final String label) {
		TargetTrakValidationError labelError = referenceDataRules.isLabelEmpty(label);
		if (labelError == null) {
			validationErrors.add(referenceDataRules.isLabelValidLength(label));
			validationErrors.add(referenceDataRules.labelContainsAllowableChars(label));
		} else {
			validationErrors.add(labelError);
		}
	}

	private void validateValue(final List<TargetTrakValidationError> validationErrors, final String value) {
		TargetTrakValidationError valueError = referenceDataRules.isValueEmpty(value);
		if (valueError == null) {
			validationErrors.add(referenceDataRules.isValueValidLength(value));
			validationErrors.add(referenceDataRules.valueContainsAllowableChars(value));
		} else {
			validationErrors.add(valueError);
		}
	}

}
