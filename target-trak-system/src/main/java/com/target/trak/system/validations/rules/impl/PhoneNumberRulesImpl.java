package com.target.trak.system.validations.rules.impl;

import java.util.Properties;

import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.rules.PhoneNumberRules;

public class PhoneNumberRulesImpl implements PhoneNumberRules {

	private Properties genericValidationProps;

	@Override
	public TargetTrakValidationError isPhoneNumberEmpty(final String telephoneNumber) {
		if (StringUtils.isEmpty(telephoneNumber)) {
			return new TargetTrakValidationError("telephoneNumber", genericValidationProps.getProperty("phoneNumber.empty.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError isPhoneNumberValidLength(final String telephoneNumber) throws IllegalArgumentException {
		int maxLength = Integer.parseInt((String) genericValidationProps.get("phonenumber.maxlength"));
		if (telephoneNumber.length() > maxLength) {
			return new TargetTrakValidationError("telephoneNumber", genericValidationProps.getProperty("phoneNumber.maxlength.error"));
		}
		return null;
	}

	@Override
	public TargetTrakValidationError phoneContainsDigitsAndDashOnly(final String telephoneNumber) throws IllegalArgumentException {
		String regex = "^[0-9]\\d{2}-\\d{3}-\\d{4}$";
		if (!telephoneNumber.matches(regex)) {
			return new TargetTrakValidationError("telephoneNumber", genericValidationProps.getProperty("phoneNumber.allowable.chars.error"));
		}
		return null;
	}

	public void setGenericValidationProps(Properties genericValidationProps) {
		this.genericValidationProps = genericValidationProps;
	}
}