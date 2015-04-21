package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.PasswordRules;

public class PasswordRulesImpl implements PasswordRules {

	private Properties validationProps;

	@Override
	public TargetTrakValidationError isPasswordEmpty(final String password) {
		if (StringUtils.isEmpty(password)) {
			return new TargetTrakValidationError("password", "REGISTRATION_004");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError repeatedPasswordEmpty(final String repeatedPassword) {
		if (StringUtils.isEmpty(repeatedPassword)) {
			return new TargetTrakValidationError("repeatedPassword", "REGISTRATION_004");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isPasswordValidLength(final String password) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("password.maxlength"));
		if (password.length() > maxLength) {
			return new TargetTrakValidationError("password", "REGISTRATION_005");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError passwordEqualsRepeatedPassword(final String password, final String repeatedPassword) {
		if (!password.equals(repeatedPassword)) {
			return new TargetTrakValidationError("password", "REGISTRATION_006");
		}
		return null;
	}

	public void setValidationProps(Properties validationProps) {
		this.validationProps = validationProps;
	}
}