package com.target.trak.system.validations.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.trak.system.security.dto.credentials.ForgotPasswordApiRequest;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.rules.EmailRules;

@Component("forgotPasswordValidator")
public class ForgotPasswordValidatorImpl implements TargetTrakValidator<ForgotPasswordApiRequest> {

	@Autowired
	private EmailRules emailRules;

	@Override
	public List<TargetTrakValidationError> validate(ForgotPasswordApiRequest request) throws IllegalArgumentException {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();
		if (request == null) {
			throw new IllegalArgumentException("API request is null");
		}

		final String email = request.getEmail();
		TargetTrakValidationError error = emailRules.isEmailEmpty(email);
		if (error == null) {
			errors.add(emailRules.isEmailValid(email));
			errors.add(emailRules.isEmailRegistered(email));
		} else {
			errors.add(error);
		}
		errors.removeAll(Collections.singleton(null));
		return errors;
	}

}
