package com.target.trak.system.service.impl.contact;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;

import com.target.trak.system.dao.ContactDao;
import com.target.trak.system.domain.ContactDomain;
import com.target.trak.system.domain.criteria.ContactSearchCriteria;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.common.TargetTrakRequestTypeEnum;
import com.target.trak.system.service.dto.contact.ContactApiRequest;
import com.target.trak.system.service.dto.contact.ContactApiResponse;
import com.target.trak.system.service.dto.contact.ContactDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;

public class GetContactsByCriteriaServiceImpl extends BaseTargetTrakService implements TargetTrakService<ContactApiRequest, ContactApiResponse>{

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
			ContactSearchCriteria criteria = conversionService.convert(request.getContactSearchCriteria(), ContactSearchCriteria.class);
			int totalSize = contactDao.selectContactsByCriteriaCount(criteria);
			if (totalSize > 0) {
				List<ContactDomain> contacts = contactDao.selectContactsByCriteria(criteria);
				List<ContactDto> dtos = convertDomainList(contacts);
				response.setContacts(dtos);
			}
			response.setTotalSize(totalSize);
			response.setSuccess(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			TargetTrakException exception = generateServiceException(response, null, TargetTrakErrorTypeEnum.ERROR, "An error has occurred processing your request. <br /> If the error still occurs, contact your administrator");
			throw exception;
		}
		
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(final ContactApiRequest request) throws TargetTrakException {
		request.setRequestType(TargetTrakRequestTypeEnum.READ_PAGING);
		List<TargetTrakValidationError> validationErrors = null;
		try {
			validationErrors =  contactValidator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e);
		}
		return validationErrors;
	}
	
	private List<ContactDto> convertDomainList(List<ContactDomain> contacts) {
		List<ContactDto> dtos = new ArrayList<ContactDto>();
		if (contacts != null && !contacts.isEmpty()) {
			for (ContactDomain contact : contacts) {
				dtos.add(conversionService.convert(contact, ContactDto.class));
			}
		}
		return dtos;
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