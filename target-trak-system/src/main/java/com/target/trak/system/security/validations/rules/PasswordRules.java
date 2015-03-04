package com.target.trak.system.security.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.validations.SecurityValidationError;

@Component("passwordRules")
public class PasswordRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;

	public SecurityValidationError isPasswordEmpty(final String password) {
		if (StringUtils.isEmpty(password)) {
			return new SecurityValidationError("password", "REGISTRATION_004");
		}
		return null;
	}

	public SecurityValidationError repeatedPasswordEmpty(final String repeatedPassword) {
		if (StringUtils.isEmpty(repeatedPassword)) {
			return new SecurityValidationError("repeatedPassword", "REGISTRATION_004");
		}
		return null;
	}

	public SecurityValidationError isPasswordValidLength(final String password) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("password.maxlength"));
		if (password.length() > maxLength) {
			return new SecurityValidationError("password", "REGISTRATION_005");
		}
		return null;
	}

	public SecurityValidationError passwordEqualsRepeatedPassword(final String password, final String repeatedPassword) {
		if (!password.equals(repeatedPassword)) {
			return new SecurityValidationError("password", "REGISTRATION_006");
		}
		return null;
	}
}
