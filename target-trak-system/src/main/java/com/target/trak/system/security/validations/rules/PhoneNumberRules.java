package com.target.trak.system.security.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.validations.SecurityValidationError;

@Component("phoneNumberRules")
public class PhoneNumberRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;
	
	public SecurityValidationError isPhoneNumberEmpty(final String mobileNumber) {
		if (StringUtils.isEmpty(mobileNumber)) {
			return new SecurityValidationError("mobileNumber", "REGISTRATION_016");
		}
		return null;
	}
	
	public SecurityValidationError isPhoneNumberValidLength(final String mobileNumber) throws IllegalArgumentException {
		int maxLength = Integer.parseInt((String)validationProps.get("phonenumber.maxlength"));
		if (mobileNumber.length() > maxLength) {
			return new SecurityValidationError("mobileNumber", "REGISTRATION_017");
		}
		return null;
	}
	
	public SecurityValidationError phoneContainsDigitsAndDashOnly(final String mobileNumber) throws IllegalArgumentException {
		String regex = "^[0-9]\\d{2}-\\d{3}-\\d{4}$";
		if (!mobileNumber.matches(regex)) {
			return new SecurityValidationError("mobileNumber", "REGISTRATION_018");
		}
		return null;
	}
}
