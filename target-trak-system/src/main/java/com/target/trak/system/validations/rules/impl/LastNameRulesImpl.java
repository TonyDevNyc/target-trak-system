package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.LastNameRules;
import com.target.trak.system.validations.util.ValidationsUtil;

public class LastNameRulesImpl implements LastNameRules {

	private Properties genericValidationProps;

	@Override
	public TargetTrakValidationError isLastNameEmpty(final String lastName) {
		if (StringUtils.isEmpty(lastName)) {
			return new TargetTrakValidationError("lastName", genericValidationProps.getProperty("lastName.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isLastNameValidLength(final String lastName) {
		int maxLength = Integer.parseInt((String) genericValidationProps.getProperty("lastname.maxlength"));
		if (lastName.length() > maxLength) {
			return new TargetTrakValidationError("lastName", genericValidationProps.getProperty("lastname.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError lastNameIsAlphabeticOnly(final String lastName) {
		String allowableChars = genericValidationProps.getProperty("lastName.allowable.chars");
		if (!StringUtils.isAlpha(lastName)) {
			String nonAlphaChars = ValidationsUtil.getNonAlphaCharacters(lastName);
			char[] allowableSpecialChars = allowableChars.toCharArray();

			if (!ValidationsUtil.containsAllowableSpecialChars(nonAlphaChars, allowableSpecialChars)) {
				return new TargetTrakValidationError("lastName", genericValidationProps.getProperty("lastName.allowable.chars.error"));
			}
		}
		return null;
	}

	public void setGenericValidationProps(Properties genericValidationProps) {
		this.genericValidationProps = genericValidationProps;
	}
}