package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.FirstNameRules;

public class FirstNameRulesImpl implements FirstNameRules {

	private Properties genericValidationProps;

	@Override
	public TargetTrakValidationError isFirstNameEmpty(final String firstName) {
		if (StringUtils.isEmpty(firstName)) {
			return new TargetTrakValidationError("firstName", genericValidationProps.getProperty("firstName.empty.error"));
		}

		if (firstName != null && StringUtils.isEmpty(firstName.trim())) {
			return new TargetTrakValidationError("firstName", genericValidationProps.getProperty("firstName.empty.error"));
		}

		return null;
	}

	@Override
	public TargetTrakValidationError isFirstNameValidLength(final String firstName) {
		int maxLength = Integer.parseInt((String) genericValidationProps.getProperty("firstname.maxlength"));
		if (firstName.length() > maxLength) {
			return new TargetTrakValidationError("firstName", genericValidationProps.getProperty("firstName.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError firstNameIsAlphabeticOnly(final String firstName) {
		if (!StringUtils.isAlpha(firstName)) {
			return new TargetTrakValidationError("firstName", genericValidationProps.getProperty("firstName.allowable.chars.error"));
		}
		return null;
	}

	public void setGenericValidationProps(Properties genericValidationProps) {
		this.genericValidationProps = genericValidationProps;
	}
}