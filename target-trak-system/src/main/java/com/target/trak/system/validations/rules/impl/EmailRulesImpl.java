package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.EmailRules;

public class EmailRulesImpl implements EmailRules {

	private Properties validationProps;
	
	private UserDetailsDao userDetailsDao;
	
	@Override
	public TargetTrakValidationError isEmailEmpty(final String email) {
		if (StringUtils.isEmpty(email)) {
			return new TargetTrakValidationError("email", "EMAIL_013");
		}
		return null;
	}
	
	@Override
	public TargetTrakValidationError isEmailValidLength(final String email) {
		int maxLength = Integer.parseInt((String)validationProps.get("email.maxlength"));
		if (email.length() > maxLength) {
			return new TargetTrakValidationError("email", "EMAIL_014");
		}
		return null;
	}
	
	@Override
	public TargetTrakValidationError isEmailValid(final String email) {
		EmailValidator validator = EmailValidator.getInstance();
		if (!validator.isValid(email)) {
			return new TargetTrakValidationError("email", "EMAIL_015");
		}
		return null;
	}
	
	@Override
	public TargetTrakValidationError emailAlreadyExists(final String email) {
		if (userDetailsDao.getUserByEmail(email) != null) {
			return new TargetTrakValidationError("email", "EMAIL_016");
		}
		return null;
	}
	
	@Override
	public TargetTrakValidationError isEmailRegistered(final String email) {
		if (userDetailsDao.getUserByEmail(email) == null) {
			return new TargetTrakValidationError("email", "EMAIL_017");
		}
		return null;
	}

	public void setValidationProps(Properties validationProps) {
		this.validationProps = validationProps;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}
}