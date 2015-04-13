package com.target.trak.system.service.impl.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.dao.CompanyDao;
import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.domain.criteria.CompanySearchCriteria;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakRequestTypeEnum;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.impl.CompanyValidatorImpl;

@Transactional(value = "dwTransactionManager", propagation = Propagation.REQUIRED)
@Service("companiesByCriteriaService")
public class GetCompaniesByCriteriaServiceImpl extends BaseTargetTrakService implements TargetTrakService<CompanyApiRequest, CompanyApiResponse> {

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
		request.setRequestType(TargetTrakRequestTypeEnum.CREATE);
		List<TargetTrakValidationError> validationErrors = validateRequest(request);
		CompanySearchCriteria criteria = conversionService.convert(request.getSearchCriteria(), CompanySearchCriteria.class);
		
		try {
			int totalSize = companyDao.selectPaginatedCompaniesCount(criteria);
			if (totalSize > 0) {
				List<CompanyDomain> companies = companyDao.selectPaginatedCompaniesByCriteria(criteria);
				List<CompanyDto> dtos = convertDomainList(companies);
			}
		} catch (Throwable e) {
			
		}
		
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(final CompanyApiRequest request) throws TargetTrakException {
		List<TargetTrakValidationError> errors = null;
		try {
			errors = validator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error("Validation error", e);
		}
		return errors;
	}
	
	private List<CompanyDto> convertDomainList(final List<CompanyDomain> companies) {
		List<CompanyDto> list = new ArrayList<>();
		
		return null;
	}

}
