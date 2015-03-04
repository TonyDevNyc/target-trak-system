package com.target.trak.system.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.security.dto.registration.RegistrationApiRequest;
import com.target.trak.system.security.dto.registration.RegistrationApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.RegistrationService;
import com.target.trak.system.security.validations.SecurityValidationError;
import com.target.trak.system.security.validations.UserRegistrationValidator;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = TargetTrakSecurityException.class)
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = Logger.getLogger(RegistrationServiceImpl.class);

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private UserRegistrationValidator validator;

	@Override
	public RegistrationApiResponse registerUser(final RegistrationApiRequest request) throws TargetTrakSecurityException {
		RegistrationApiResponse response = new RegistrationApiResponse();
		List<SecurityValidationError> validations = validator.validate(request);

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

}