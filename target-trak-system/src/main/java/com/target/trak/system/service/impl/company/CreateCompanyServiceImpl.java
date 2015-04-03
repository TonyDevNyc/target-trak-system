package com.target.trak.system.service.impl.company;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.dao.CompanyDao;
import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.service.AbstractTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.CompanyValidatorImpl;

@Transactional(value = "dwTransactionManager", propagation = Propagation.REQUIRED)
public class CreateCompanyServiceImpl extends AbstractTargetTrakService implements TargetTrakService<CompanyApiRequest, CompanyApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CompanyDao companyDao;
	
	@Qualifier("companyValidator")
	@Autowired
	private CompanyValidatorImpl validator;
	
	@Autowired
	private ConversionService conversionService;
	
	@Override
	public CompanyApiResponse executeRequest(final CompanyApiRequest request) throws TargetTrakException {
		CompanyApiResponse response = new CompanyApiResponse();
		try {
			CompanyDomain domain = companyDao.insertCompany(conversionService.convert(request.getCompany(), CompanyDomain.class));
			response.setCompany(conversionService.convert(domain, CompanyDto.class));
			response.setSuccess(Boolean.TRUE);
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
			TargetTrakException exception = generateServiceException(null, TargetTrakErrorTypeEnum.ERROR, "An error has occurred processing your request. <br /> If the error still occurs, contact your administrator");
			throw exception;
		}
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(final CompanyApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> errors = null;
		try {
			errors =  validator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error("Validation error", e);
		}
		return errors;
	}
}