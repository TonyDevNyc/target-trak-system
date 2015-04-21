package com.target.trak.system.service.impl.company;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.dao.CompanyDao;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.impl.CompanyValidatorImpl;

@Transactional(value = "dwTransactionManager", propagation = Propagation.REQUIRED)
public class UpdateCompanyServiceImpl implements TargetTrakService<CompanyApiRequest, CompanyApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());

	private CompanyDao companyDao;
	
	private CompanyValidatorImpl validator;
	
	private ConversionService conversionService;
	
	@Override
	public CompanyApiResponse executeRequest(CompanyApiRequest request) throws TargetTrakException {
		
		return null;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(CompanyApiRequest request) throws TargetTrakException {
		
		return null;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setValidator(CompanyValidatorImpl validator) {
		this.validator = validator;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
}