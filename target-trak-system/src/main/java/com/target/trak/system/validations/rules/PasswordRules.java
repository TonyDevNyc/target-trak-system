package com.target.trak.system.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;

@Component("passwordRules")
public class PasswordRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;

	public TargetTrakValidationError isPasswordEmpty(final String password) {
		if (StringUtils.isEmpty(password)) {
			return new TargetTrakValidationError("password", "REGISTRATION_004");
		}
		return null;
	}

	public TargetTrakValidationError repeatedPasswordEmpty(final String repeatedPassword) {
		if (StringUtils.isEmpty(repeatedPassword)) {
			return new TargetTrakValidationError("repeatedPassword", "REGISTRATION_004");
		}
		return null;
	}

	public TargetTrakValidationError isPasswordValidLength(final String password) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("password.maxlength"));
		if (password.length() > maxLength) {
			return new TargetTrakValidationError("password", "REGISTRATION_005");
		}
		return null;
	}

	public TargetTrakValidationError passwordEqualsRepeatedPassword(final String password, final String repeatedPassword) {
		if (!password.equals(repeatedPassword)) {
			return new TargetTrakValidationError("password", "REGISTRATION_006");
		}
		return null;
	}
}
