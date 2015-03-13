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
import com.target.trak.system.service.ReferenceDataService;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.ReferenceDataValidatorImpl;

@Transactional(value="dwTransactionManager", propagation=Propagation.REQUIRED, rollbackFor=TargetTrakException.class)
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
	
	@Override
	public ReferenceDataApiResponse createReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		List<TargetTrakValidationError> validationErrors = validateRequest(request);
		
		if (validationErrors.isEmpty()) {
			ReferenceDataDomain domain = referenceDataDao.insertReferenceData(conversionService.convert(request.getReferenceDataDto(), ReferenceDataDomain.class));
			response.setReferenceData(conversionService.convert(domain, ReferenceDataDto.class));
			response.setSuccess(Boolean.TRUE);
		} else {
			response.setSuccess(Boolean.FALSE);
			response.setErrors(validationErrors);
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

	@Transactional(propagation=Propagation.NEVER)
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
		
		return null;
	}
	
	private List<ReferenceDataDto> convertListOfDomains(final List<ReferenceDataDomain> domains) {
		List<ReferenceDataDto> dtos = new ArrayList<ReferenceDataDto>();
		for (ReferenceDataDomain domain : domains) {
			dtos.add(conversionService.convert(domain, ReferenceDataDto.class));
		}
		return dtos;
	}

}