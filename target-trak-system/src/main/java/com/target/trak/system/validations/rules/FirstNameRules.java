package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface FirstNameRules {

	public TargetTrakValidationError isFirstNameEmpty(final String firstName);

	public TargetTrakValidationError isFirstNameValidLength(final String firstName);
	
	public TargetTrakValidationError firstNameIsAlphabeticOnly(final String firstName);
}
