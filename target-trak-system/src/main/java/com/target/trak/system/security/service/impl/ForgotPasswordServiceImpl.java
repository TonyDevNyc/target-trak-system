package com.target.trak.system.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.dto.forgotpw.ForgotPasswordApiRequest;
import com.target.trak.system.security.service.dto.forgotpw.ForgotPasswordApiResponse;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.impl.ForgotPasswordValidatorImpl;

@Transactional(value = "securityTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = TargetTrakSecurityException.class)
public class ForgotPasswordServiceImpl extends BaseTargetTrakService implements TargetTrakService<ForgotPasswordApiRequest, ForgotPasswordApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());

	private TargetTrakValidator<ForgotPasswordApiRequest> forgotPasswordValidator;

	@Override
	public ForgotPasswordApiResponse processRequest(final ForgotPasswordApiRequest request) throws TargetTrakException {
		ForgotPasswordApiResponse response = new ForgotPasswordApiResponse();
		List<TargetTrakValidationError> validationErrors = validateRequest(request);
		
		if (!validationErrors.isEmpty()) {
			response.setSuccess(Boolean.FALSE);
			response.setErrors(validationErrors);
		} else {
			response.setSuccess(Boolean.TRUE);
			// send email
		}
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(final ForgotPasswordApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> validationErrors = null;
		try {
			validationErrors = forgotPasswordValidator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e);
		}
		return validationErrors;
	}

	public void setForgotPasswordValidator(ForgotPasswordValidatorImpl forgotPasswordValidator) {
		this.forgotPasswordValidator = forgotPasswordValidator;
	}
}