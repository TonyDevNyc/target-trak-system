package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface LastNameRules {

	public TargetTrakValidationError isLastNameEmpty(final String lastName);

	public TargetTrakValidationError isLastNameValidLength(final String lastName);
	
	public TargetTrakValidationError lastNameIsAlphabeticOnly(final String lastName);
}
