package com.target.trak.system.security.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.security.validations.SecurityValidationError;

@Component("usernameRules")
public class UsernameRules {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;

	public SecurityValidationError isUsernameEmpty(final String username) {
		if (StringUtils.isEmpty(username)) {
			return new SecurityValidationError("username", "REGISTRATION_000");
		}
		return null;
	}

	public SecurityValidationError isUsernameValidLength(final String username) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("username.maxlength"));
		if (username.length() > maxLength) {
			return new SecurityValidationError("username", "REGISTRATION_001");
		}
		return null;
	}
	
	public SecurityValidationError usernameIsAlphabeticOnly(final String username) {
		if (!username.matches("[a-z]+")) {
			return new SecurityValidationError("username", "REGISTRATION_003");
		}
		return null;
	}
	
	public SecurityValidationError usernameAlreadyExists(final String username) {
		TargetTrakUser user = userDetailsDao.getUserByUsername(username);
		if (user != null) {
			return new SecurityValidationError("username", "REGISTRATION_002");
		}
		return null;
	}
}
