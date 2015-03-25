package com.target.trak.system.validations.rules;

import com.target.trak.system.validations.TargetTrakValidationError;

public interface PhoneNumberRules {

	public TargetTrakValidationError isPhoneNumberEmpty(final String mobileNumber);
	
	public TargetTrakValidationError isPhoneNumberValidLength(final String mobileNumber) throws IllegalArgumentException;
	
	public TargetTrakValidationError phoneContainsDigitsAndDashOnly(final String mobileNumber) throws IllegalArgumentException;
}
