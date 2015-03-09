package com.target.trak.system.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;

@Component("firstNameRules")
public class FirstNameRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;

	public TargetTrakValidationError isFirstNameEmpty(final String firstName) {
		if (StringUtils.isEmpty(firstName)) {
			return new TargetTrakValidationError("firstName", "REGISTRATION_007");
		}
		return null;
	}

	public TargetTrakValidationError isFirstNameValidLength(final String firstName) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("firstname.maxlength"));
		if (firstName.length() > maxLength) {
			return new TargetTrakValidationError("firstName", "REGISTRATION_008");
		}
		return null;
	}
	
	public TargetTrakValidationError firstNameIsAlphabeticOnly(final String firstName) {
		if (!firstName.matches("[a-zA-Z]+")) {
			return new TargetTrakValidationError("firstName", "REGISTRATION_009");
		}
		return null;
	}
}
