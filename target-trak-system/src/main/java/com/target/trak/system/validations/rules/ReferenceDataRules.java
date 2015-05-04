package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface ReferenceDataRules {

	public TargetTrakValidationError isIdEmpty(final Long id);
	
	public TargetTrakValidationError isTypeEmpty(final String type);

	public TargetTrakValidationError isTypeValidLength(final String type);

	public TargetTrakValidationError typeContainsAllowableChars(final String type);

	public TargetTrakValidationError isLabelEmpty(final String label);

	public TargetTrakValidationError isLabelValidLength(final String label);

	public TargetTrakValidationError labelContainsAllowableChars(final String label);

	public TargetTrakValidationError isValueEmpty(final String value);

	public TargetTrakValidationError isValueValidLength(final String value);

	public TargetTrakValidationError valueContainsAllowableChars(final String value);
	
	public TargetTrakValidationError referenceDataAlreadyExists(final String type, final String label, final String value);
	
	public TargetTrakValidationError checkReferenceDataConstraint(final Long requestId, final String type, final String label, final String value);
	
	public TargetTrakValidationError isStatusEmpty(final String status);
	
	public TargetTrakValidationError isStatusValidLength(final String status);
	
	public TargetTrakValidationError containsAllowableStatus(final String status);
}
