package com.target.trak.system.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.dto.registration.RegistrationApiRequest;
import com.target.trak.system.security.service.dto.registration.RegistrationApiResponse;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;
import com.target.trak.system.validations.impl.UserRegistrationValidatorImpl;

@Transactional(value = "securityTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = TargetTrakSecurityException.class)
public class RegistrationServiceImpl extends BaseTargetTrakService implements TargetTrakService<RegistrationApiRequest, RegistrationApiResponse> {

	private static final Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

	private UserDetailsDao userDetailsDao;

	private ConversionService conversionService;

	private TargetTrakValidator<RegistrationApiRequest> registrationValidator;

	@Override
	public RegistrationApiResponse processRequest(final RegistrationApiRequest request) throws TargetTrakException {
		RegistrationApiResponse response = new RegistrationApiResponse();
		List<TargetTrakValidationError> validationErrors = validateRequest(request);
		
		if (!validationErrors.isEmpty()) {
			response.setSuccess(Boolean.FALSE);
			response.setErrors(validationErrors);
		} else {
			try {
				TargetTrakUser user = conversionService.convert(request.getUserRegistration(), TargetTrakUser.class);
				userDetailsDao.insertTargetTrakUser(user);
				response.setSuccess(Boolean.TRUE);
			} catch (Throwable e) {
				logger.error(e.getMessage(), e);
				TargetTrakException exception = generateServiceException(response, validationErrors, TargetTrakErrorTypeEnum.ERROR, "An error has occurred trying to create Reference Data. <br /> If the error still occurs, contact your administrator");
				throw exception;
			}
		}
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(final RegistrationApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> validationErrors = null;
		try {
			validationErrors = registrationValidator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e);
		}
		return validationErrors;
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