package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.UsernameRules;

@Component("usernameRules")
public class UsernameRulesImpl implements UsernameRules {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;

	@Override
	public TargetTrakValidationError isUsernameEmpty(final String username) {
		if (StringUtils.isEmpty(username)) {
			return new TargetTrakValidationError("username", "REGISTRATION_000");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isUsernameValidLength(final String username) {
		int maxLength = Integer.parseInt((String) validationProps.getProperty("username.maxlength"));
		if (username.length() > maxLength) {
			return new TargetTrakValidationError("username", "REGISTRATION_001");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError usernameIsAlphabeticOnly(final String username) {
		if (!username.matches("[a-z]+")) {
			return new TargetTrakValidationError("username", "REGISTRATION_003");
		}
		return null;
	}

	@Override
	public TargetTrakValidationError usernameAlreadyExists(final String username) {
		TargetTrakUser user = userDetailsDao.getUserByUsername(username);
		if (user != null) {
			return new TargetTrakValidationError("username", "REGISTRATION_002");
		}
		return null;
	}
}
