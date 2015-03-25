package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.LastNameRules;

@Component("lastNameRules")
public class LastNameRulesImpl implements LastNameRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;

	@Override
	public TargetTrakValidationError isLastNameEmpty(final String lastName) {
		if (StringUtils.isEmpty(lastName)) {
			return new TargetTrakValidationError("lastName", "REGISTRATION_010");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isLastNameValidLength(final String lastName) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("lastname.maxlength"));
		if (lastName.length() > maxLength) {
			return new TargetTrakValidationError("lastName", "REGISTRATION_011");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError lastNameIsAlphabeticOnly(final String lastName) {
		if (!lastName.matches("[a-zA-Z]+")) {
			return new TargetTrakValidationError("lastName", "REGISTRATION_012");
		}
		return null;
	}
}
