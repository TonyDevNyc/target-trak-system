package com.target.trak.system.validations.rules;

import java.util.Properties;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.validations.TargetTrakValidationError;

@Component("emailRules")
public class EmailRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	public TargetTrakValidationError isEmailEmpty(final String email) {
		if (StringUtils.isEmpty(email)) {
			return new TargetTrakValidationError("email", "EMAIL_013");
		}
		return null;
	}
	
	public TargetTrakValidationError isEmailValidLength(final String email) {
		int maxLength = Integer.parseInt((String)validationProps.get("email.maxlength"));
		if (email.length() > maxLength) {
			return new TargetTrakValidationError("email", "EMAIL_014");
		}
		return null;
	}
	
	public TargetTrakValidationError isEmailValid(final String email) {
		EmailValidator validator = EmailValidator.getInstance();
		if (!validator.isValid(email)) {
			return new TargetTrakValidationError("email", "EMAIL_015");
		}
		return null;
	}
	
	public TargetTrakValidationError emailAlreadyExists(final String email) {
		if (userDetailsDao.getUserByEmail(email) != null) {
			return new TargetTrakValidationError("email", "EMAIL_016");
		}
		return null;
	}
	
	public TargetTrakValidationError isEmailRegistered(final String email) {
		if (userDetailsDao.getUserByEmail(email) == null) {
			return new TargetTrakValidationError("email", "EMAIL_017");
		}
		return null;
	}
}
