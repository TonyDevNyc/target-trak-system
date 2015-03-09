package com.target.trak.system.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.dto.credentials.ForgotPasswordApiRequest;
import com.target.trak.system.security.dto.credentials.ForgotPasswordApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.ForgotPasswordService;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.impl.ForgotPasswordValidatorImpl;

@Transactional(value="securityTransactionManager", propagation=Propagation.REQUIRED, rollbackFor=TargetTrakSecurityException.class)
@Service("forgotPasswordService")
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Qualifier("forgotPasswordValidator")
	@Autowired
	private ForgotPasswordValidatorImpl validator;
	
	@Override
	public ForgotPasswordApiResponse handleForgotPassword(final ForgotPasswordApiRequest request) throws TargetTrakSecurityException {
		ForgotPasswordApiResponse response = new ForgotPasswordApiResponse();
		List<TargetTrakValidationError> validationErrors = validator.validate(request);
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

}
