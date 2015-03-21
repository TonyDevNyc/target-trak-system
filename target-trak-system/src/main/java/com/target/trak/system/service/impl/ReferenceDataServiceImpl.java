package com.target.trak.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.domain.ReferenceDataSearchCriteria;
import com.target.trak.system.security.audit.AuditableEvent;
import com.target.trak.system.service.ReferenceDataService;
import com.target.trak.system.service.dto.TargetTrakRequestTypeEnum;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.ReferenceDataValidatorImpl;

@Transactional(value = "dwTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = TargetTrakException.class)
@Service("referenceDataService")
public class ReferenceDataServiceImpl implements ReferenceDataService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ReferenceDataDao referenceDataDao;

	@Autowired
	private ConversionService conversionService;

	@Qualifier("referenceDataValidator")
	@Autowired
	private ReferenceDataValidatorImpl validator;

	@AuditableEvent(auditableEventCode="Create Reference Data", auditableEventMessage = "Create Reference Data", auditableEventUser = "tina")
	@Override
	public ReferenceDataApiResponse createReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		request.setRequestType(TargetTrakRequestTypeEnum.CREATE);
		List<TargetTrakValidationError> validationErrors = validateRequest(request);
		try {
			if (validationErrors.isEmpty()) {
				ReferenceDataDomain domain = referenceDataDao.insertReferenceData(conversionService.convert(request.getReferenceDataDto(), ReferenceDataDomain.class));
				response.setReferenceData(conversionService.convert(domain, ReferenceDataDto.class));
				response.setSuccess(Boolean.TRUE);

			} else {
				response.setMessage("A validation error has occurred. Please fix the errors below");
				response.setSuccess(Boolean.FALSE);
				response.setErrors(validationErrors);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setSuccess(Boolean.FALSE);
			response.setMessage("An error has occurred trying to create Reference Data. <br /> If the error still occurs, contact your administrator");
		}
		return response;
	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public ReferenceDataApiResponse getReferenceDataByCriteria(final ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		ReferenceDataSearchCriteria criteria = conversionService.convert(request.getSearchCriteria(), ReferenceDataSearchCriteria.class);

		int totalSize = referenceDataDao.selectReferenceDataBySearchCriteriaCount(criteria);
		if (totalSize > 0) {
			List<ReferenceDataDomain> data = referenceDataDao.selectPaginatedReferenceDataBySearchCriteria(criteria);
			List<ReferenceDataDto> dtos = convertListOfDomains(data);
			response.setReferenceDataList(dtos);
		}
		response.setTotalSize(totalSize);
		response.setSuccess(Boolean.TRUE);

		return response;
	}

	@Transactional(propagation = Propagation.NEVER)
	@Override
	public ReferenceDataApiResponse getReferenceDataTypes() {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		List<ReferenceDataDomain> data = referenceDataDao.getReferenceDataTypes();
		List<ReferenceDataDto> dtos = convertListOfDomains(data);
		response.setSuccess(Boolean.TRUE);
		response.setReferenceDataList(dtos);
		return response;
	}

	@Override
	public ReferenceDataApiResponse updateReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		request.setRequestType(TargetTrakRequestTypeEnum.UPDATE);
		List<TargetTrakValidationError> validationErrors = validateRequest(request);

		try {
			if (validationErrors.isEmpty()) {
				ReferenceDataDto requestDto = request.getReferenceDataDto();
				ReferenceDataDomain domain = referenceDataDao.updateReferenceData(conversionService.convert(requestDto, ReferenceDataDomain.class));
				ReferenceDataDto dto = conversionService.convert(domain, ReferenceDataDto.class);
				response.setReferenceData(dto);
				response.setSuccess(Boolean.TRUE);
			} else {
				response.setMessage("A validation error has occurred. Please fix the errors below");
				response.setSuccess(Boolean.FALSE);
				response.setErrors(validationErrors);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setSuccess(Boolean.FALSE);
			response.setMessage("An error has occurred trying to update Reference Data. <br /> If the error still occurs, contact your administrator");
		}
		return response;
	}

	@Override
	public ReferenceDataApiResponse deleteReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		request.setRequestType(TargetTrakRequestTypeEnum.DELETE);
		List<TargetTrakValidationError> validationErrors = validateRequest(request);

		try {
			if (validationErrors.isEmpty()) {
				ReferenceDataDto requestDto = request.getReferenceDataDto();
				referenceDataDao.deleteReferenceData(conversionService.convert(requestDto, ReferenceDataDomain.class));
				response.setSuccess(Boolean.TRUE);
			} else {
				response.setMessage("A validation error has occurred. Please fix the errors below");
				response.setSuccess(Boolean.FALSE);
				response.setErrors(validationErrors);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setSuccess(Boolean.FALSE);
			response.setMessage("An error has occurred trying to delete Reference Data. <br /> If the error still occurs, contact your administrator");
		}
		return response;
	}

	private List<TargetTrakValidationError> validateRequest(final ReferenceDataApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> validations = new ArrayList<TargetTrakValidationError>();
		try {
			validations = validator.validate(request);
			return validations;
		} catch (TargetTrakValidationException e) {
			logger.error(e.getMessage(), e);
			throw new TargetTrakException(e.getMessage());
		}
	}

	private List<ReferenceDataDto> convertListOfDomains(final List<ReferenceDataDomain> domains) {
		List<ReferenceDataDto> dtos = new ArrayList<ReferenceDataDto>();
		for (ReferenceDataDomain domain : domains) {
			dtos.add(conversionService.convert(domain, ReferenceDataDto.class));
		}
		return dtos;
	}
}