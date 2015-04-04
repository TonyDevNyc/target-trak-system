package com.target.trak.system.service.impl.referencedata;

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
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.common.TargetTrakRequestTypeEnum;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.ReferenceDataValidatorImpl;

@Transactional(value = "dwTransactionManager", propagation = Propagation.REQUIRED)
@Service("createReferenceDataService")
public class CreateReferenceDataServiceImpl extends BaseTargetTrakService implements TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ReferenceDataDao referenceDataDao;

	@Autowired
	private ConversionService conversionService;

	@Qualifier("referenceDataValidator")
	@Autowired
	private ReferenceDataValidatorImpl validator;
	
	@Override
	public ReferenceDataApiResponse executeRequest(ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		request.setRequestType(TargetTrakRequestTypeEnum.CREATE);
		List<TargetTrakValidationError> validationErrors = validateRequest(request);

		if (!validationErrors.isEmpty()) {
			TargetTrakException exception = generateServiceException(response, validationErrors, TargetTrakErrorTypeEnum.VALIDATION, "A validation error has occurred. Please fix the errors below");
			throw exception;
		}

		try {
			ReferenceDataDomain domain = referenceDataDao.insertReferenceData(conversionService.convert(request.getReferenceDataDto(), ReferenceDataDomain.class));
			response.setReferenceData(conversionService.convert(domain, ReferenceDataDto.class));
			response.setSuccess(Boolean.TRUE);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			TargetTrakException exception = generateServiceException(response, validationErrors, TargetTrakErrorTypeEnum.ERROR, "An error has occurred trying to create Reference Data. <br /> If the error still occurs, contact your administrator");
			throw exception;
		}
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(ReferenceDataApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> validationErrors = null;
		try {
			validationErrors =  validator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error(e);
		}
		return validationErrors;
	}

}
