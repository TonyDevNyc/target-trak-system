package com.target.trak.system.security.validations.rules;

import java.util.Properties;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.validations.SecurityValidationError;

@Component("emailRules")
public class EmailRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	public SecurityValidationError isEmailEmpty(final String email) {
		if (StringUtils.isEmpty(email)) {
			return new SecurityValidationError("email", "EMAIL_013");
		}
		return null;
	}
	
	public SecurityValidationError isEmailValidLength(final String email) {
		int maxLength = Integer.parseInt((String)validationProps.get("email.maxlength"));
		if (email.length() > maxLength) {
			return new SecurityValidationError("email", "EMAIL_014");
		}
		return null;
	}
	
	public SecurityValidationError isEmailValid(final String email) {
		EmailValidator validator = EmailValidator.getInstance();
		if (!validator.isValid(email)) {
			return new SecurityValidationError("email", "EMAIL_015");
		}
		return null;
	}
	
	public SecurityValidationError emailAlreadyExists(final String email) {
		if (userDetailsDao.getUserByEmail(email) != null) {
			return new SecurityValidationError("email", "EMAIL_016");
		}
		return null;
	}
	
	public SecurityValidationError isEmailRegistered(final String email) {
		if (userDetailsDao.getUserByEmail(email) == null) {
			return new SecurityValidationError("email", "EMAIL_017");
		}
		return null;
	}
}
