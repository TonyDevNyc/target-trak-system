package com.target.trak.system.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.security.dto.registration.RegistrationApiRequest;
import com.target.trak.system.security.dto.registration.RegistrationApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.RegistrationService;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.UserRegistrationValidatorImpl;

@Transactional(value = "securityTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = TargetTrakSecurityException.class)
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

	private UserDetailsDao userDetailsDao;

	private ConversionService conversionService;

	private UserRegistrationValidatorImpl registrationValidator;

	@Override
	public RegistrationApiResponse registerUser(final RegistrationApiRequest request) throws TargetTrakSecurityException {
		RegistrationApiResponse response = new RegistrationApiResponse();
		List<TargetTrakValidationError> validations = null;
		try {
			validations = registrationValidator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e.getMessage(), e);
			throw new TargetTrakSecurityException(e.getMessage());
		}

		if (validations.isEmpty()) {
			TargetTrakUser user = conversionService.convert(request.getUserRegistration(), TargetTrakUser.class);
			try {
				userDetailsDao.insertTargetTrakUser(user);
				response.setSuccess(Boolean.TRUE);
			} catch (DataAccessException dae) {
				logger.error("Error occurred while trying to register user", dae);
				throw new TargetTrakSecurityException(dae.getMessage());
			}
		} else {
			response.setSuccess(Boolean.FALSE);
			response.setErrors(validations);
		}
		return response;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void setRegistrationValidator(UserRegistrationValidatorImpl registrationValidator) {
		this.registrationValidator = registrationValidator;
	}

}