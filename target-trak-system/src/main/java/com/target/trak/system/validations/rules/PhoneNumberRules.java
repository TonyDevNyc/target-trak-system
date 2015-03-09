package com.target.trak.system.validations.rules;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.validations.TargetTrakValidationError;

@Component("phoneNumberRules")
public class PhoneNumberRules {

	@Autowired
	@Qualifier("validationProps")
	private Properties validationProps;
	
	public TargetTrakValidationError isPhoneNumberEmpty(final String mobileNumber) {
		if (StringUtils.isEmpty(mobileNumber)) {
			return new TargetTrakValidationError("mobileNumber", "REGISTRATION_016");
		}
		return null;
	}
	
	public TargetTrakValidationError isPhoneNumberValidLength(final String mobileNumber) throws IllegalArgumentException {
		int maxLength = Integer.parseInt((String)validationProps.get("phonenumber.maxlength"));
		if (mobileNumber.length() > maxLength) {
			return new TargetTrakValidationError("mobileNumber", "REGISTRATION_017");
		}
		return null;
	}
	
	public TargetTrakValidationError phoneContainsDigitsAndDashOnly(final String mobileNumber) throws IllegalArgumentException {
		String regex = "^[0-9]\\d{2}-\\d{3}-\\d{4}$";
		if (!mobileNumber.matches(regex)) {
			return new TargetTrakValidationError("mobileNumber", "REGISTRATION_018");
		}
		return null;
	}
}
