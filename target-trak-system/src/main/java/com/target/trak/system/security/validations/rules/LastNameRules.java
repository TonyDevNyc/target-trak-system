package com.target.trak.system.security.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.validations.SecurityValidationError;

@Component("lastNameRules")
public class LastNameRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;
	
	public SecurityValidationError isLastNameEmpty(final String lastName) {
		if (StringUtils.isEmpty(lastName)) {
			return new SecurityValidationError("lastName", "REGISTRATION_010");
		}
		return null;
	}

	public SecurityValidationError isLastNameValidLength(final String lastName) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("lastname.maxlength"));
		if (lastName.length() > maxLength) {
			return new SecurityValidationError("lastName", "REGISTRATION_011");
		}
		return null;
	}
	
	public SecurityValidationError lastNameIsAlphabeticOnly(final String lastName) {
		if (!lastName.matches("[a-zA-Z]+")) {
			return new SecurityValidationError("lastName", "REGISTRATION_012");
		}
		return null;
	}
}
