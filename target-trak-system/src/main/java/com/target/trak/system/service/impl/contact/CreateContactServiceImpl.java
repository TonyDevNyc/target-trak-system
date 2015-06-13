package com.target.trak.system.service.impl.contact;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.dao.ContactDao;
import com.target.trak.system.domain.ContactDomain;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.contact.ContactApiRequest;
import com.target.trak.system.service.dto.contact.ContactApiResponse;
import com.target.trak.system.service.dto.contact.ContactDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;

@Transactional(value = "dwTransactionManager", propagation = Propagation.REQUIRED)
public class CreateContactServiceImpl extends BaseTargetTrakService implements TargetTrakService<ContactApiRequest, ContactApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());
	
	private ContactDao contactDao;
	
	private ConversionService conversionService;
	
	private TargetTrakValidator<ContactApiRequest> contactValidator;
	
	@Override
	public ContactApiResponse processRequest(final ContactApiRequest request) throws TargetTrakException {
		ContactApiResponse response = new ContactApiResponse();
		List<TargetTrakValidationError> validationErrors = validateRequest(request);
		
		if (!validationErrors.isEmpty()) {
			TargetTrakException exception = generateServiceException(response, validationErrors, TargetTrakErrorTypeEnum.VALIDATION, "A validation error has occurred. Please fix the errors below");
			throw exception;
		}

		try {
			ContactDomain domain = contactDao.insertContact(conversionService.convert(request.getContact(), ContactDomain.class));
			response.setContact(conversionService.convert(domain, ContactDto.class));
			response.setSuccess(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			TargetTrakException exception = generateServiceException(response, validationErrors, TargetTrakErrorTypeEnum.ERROR, "An error has occurred trying to create Reference Data. <br /> If the error still occurs, contact your administrator");
			throw exception;
		}
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(final ContactApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> validationErrors = null;
		try {
			validationErrors =  contactValidator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e);
		}
		return validationErrors;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void setContactValidator(TargetTrakValidator<ContactApiRequest> contactValidator) {
		this.contactValidator = contactValidator;
	}
}