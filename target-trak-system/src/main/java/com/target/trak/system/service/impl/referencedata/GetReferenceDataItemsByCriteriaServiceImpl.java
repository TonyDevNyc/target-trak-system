package com.target.trak.system.service.impl.referencedata;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.domain.criteria.ReferenceDataSearchCriteria;
import com.target.trak.system.security.audit.AuditableEvent;
import com.target.trak.system.security.audit.TargetTrakAuditEventCode;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.ReferenceDataValidatorImpl;

public class GetReferenceDataItemsByCriteriaServiceImpl extends BaseTargetTrakService implements TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());

	private ReferenceDataDao referenceDataDao;

	private ConversionService conversionService;

	private ReferenceDataValidatorImpl validator;

	@AuditableEvent(auditableEventCode=TargetTrakAuditEventCode.SEARCH_REFERENCE_DATA_BY_CRITERIA)
	@Override
	public ReferenceDataApiResponse processRequest(ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		ReferenceDataSearchCriteria criteria = conversionService.convert(request.getSearchCriteria(), ReferenceDataSearchCriteria.class);
		try {
			int totalSize = referenceDataDao.selectReferenceDataBySearchCriteriaCount(criteria);
			if (totalSize > 0) {
				List<ReferenceDataDomain> data = referenceDataDao.selectPaginatedReferenceDataBySearchCriteria(criteria);
				List<ReferenceDataDto> dtos = convertListOfDomains(data);
				response.setReferenceDataList(dtos);
			} else {
				response.setReferenceDataList(new ArrayList<ReferenceDataDto>());
			}
			response.setTotalSize(totalSize);
			response.setSuccess(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			TargetTrakException exception = generateServiceException(response, null, TargetTrakErrorTypeEnum.ERROR, "An error has occurred trying to search Reference Data. <br /> If the error still occurs, contact your administrator");
			throw exception;
		}
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(ReferenceDataApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> validationErrors = null;
		try {
			validationErrors = validator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e);
		}
		return validationErrors;
	}

	private List<ReferenceDataDto> convertListOfDomains(final List<ReferenceDataDomain> domains) {
		List<ReferenceDataDto> dtos = new ArrayList<ReferenceDataDto>();
		for (ReferenceDataDomain domain : domains) {
			dtos.add(conversionService.convert(domain, ReferenceDataDto.class));
		}
		return dtos;
	}

	public void setReferenceDataDao(ReferenceDataDao referenceDataDao) {
		this.referenceDataDao = referenceDataDao;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void setValidator(ReferenceDataValidatorImpl validator) {
		this.validator = validator;
	}
}