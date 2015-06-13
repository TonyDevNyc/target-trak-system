package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface PhoneNumberRules {

	public TargetTrakValidationError isPhoneNumberEmpty(final String telephoneNumber);
	
	public TargetTrakValidationError isPhoneNumberValidLength(final String telephoneNumber);
	
	public TargetTrakValidationError phoneContainsDigitsAndDashOnly(final String telephoneNumber);
}
