package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.FirstNameRules;

public class FirstNameRulesImpl implements FirstNameRules {

	private Properties validationProps;

	@Override
	public TargetTrakValidationError isFirstNameEmpty(final String firstName) {
		if (StringUtils.isEmpty(firstName)) {
			return new TargetTrakValidationError("firstName", "REGISTRATION_007");
		}
		
		if (firstName != null && StringUtils.isEmpty(firstName.trim())) {
			return new TargetTrakValidationError("firstName", "REGISTRATION_007");
		}
		
		return null;
	}

	@Override
	public TargetTrakValidationError isFirstNameValidLength(final String firstName) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("firstname.maxlength"));
		if (firstName.length() > maxLength) {
			return new TargetTrakValidationError("firstName", "REGISTRATION_008");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError firstNameIsAlphabeticOnly(final String firstName) {
		if (!firstName.matches("[a-zA-Z]+")) {
			return new TargetTrakValidationError("firstName", "REGISTRATION_009");
		}
		return null;
	}

	public void setValidationProps(Properties validationProps) {
		this.validationProps = validationProps;
	}
}