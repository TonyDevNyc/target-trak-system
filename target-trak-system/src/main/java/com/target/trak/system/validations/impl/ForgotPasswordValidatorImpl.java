package com.target.trak.system.validations.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.target.trak.system.security.service.dto.forgotpw.ForgotPasswordApiRequest;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.rules.EmailRules;

public class ForgotPasswordValidatorImpl implements TargetTrakValidator<ForgotPasswordApiRequest> {

	private EmailRules emailRules;

	public ForgotPasswordValidatorImpl(EmailRules emailRules) {
		this.emailRules = emailRules;
	}

	@Override
	public List<TargetTrakValidationError> validate(final ForgotPasswordApiRequest request) throws TargetTrakValidationException {
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();
		if (request == null) {
			throw new TargetTrakValidationException("API request is null");
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
