package com.target.trak.system.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.dto.credentials.ForgotPasswordApiRequest;
import com.target.trak.system.security.dto.credentials.ForgotPasswordApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.ForgotPasswordService;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.ForgotPasswordValidatorImpl;

@Transactional(value="securityTransactionManager", propagation=Propagation.REQUIRED, rollbackFor=TargetTrakSecurityException.class)
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	private final Logger logger = Logger.getLogger(getClass());

	private ForgotPasswordValidatorImpl forgotPasswordValidator;
	
	@Override
	public ForgotPasswordApiResponse handleForgotPassword(final ForgotPasswordApiRequest request) throws TargetTrakSecurityException {
		ForgotPasswordApiResponse response = new ForgotPasswordApiResponse();
		List<TargetTrakValidationError> validationErrors = null;
		try {
			validationErrors = forgotPasswordValidator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e.getMessage(), e);
			throw new TargetTrakSecurityException(e.getMessage());
		}
		
		if (validationErrors.isEmpty()) {
			// audit log
			
			// Generate Random Password
			
			// send email
			
			response.setSuccess(Boolean.TRUE);
		} else {
			response.setSuccess(Boolean.FALSE);
			response.setErrors(validationErrors);
		}
		return response;
	}

	public void setForgotPasswordValidator(ForgotPasswordValidatorImpl forgotPasswordValidator) {
		this.forgotPasswordValidator = forgotPasswordValidator;
	}

}
