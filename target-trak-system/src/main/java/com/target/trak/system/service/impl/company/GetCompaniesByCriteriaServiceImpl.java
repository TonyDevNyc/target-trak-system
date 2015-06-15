package com.target.trak.system.service.impl.company;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.dao.CompanyDao;
import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.domain.criteria.CompanySearchCriteria;
import com.target.trak.system.security.audit.AuditableEvent;
import com.target.trak.system.security.audit.TargetTrakAuditEventCode;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.common.TargetTrakErrorTypeEnum;
import com.target.trak.system.service.dto.common.TargetTrakRequestTypeEnum;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;
import com.target.trak.system.validations.TargetTrakValidationException;
import com.target.trak.system.validations.TargetTrakValidator;

@Transactional(value = "dwTransactionManager", propagation = Propagation.REQUIRED)
public class GetCompaniesByCriteriaServiceImpl extends BaseTargetTrakService implements TargetTrakService<CompanyApiRequest, CompanyApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());

	private CompanyDao companyDao;

	private TargetTrakValidator<CompanyApiRequest> validator;

	private ConversionService conversionService;

	@AuditableEvent(auditableEventCode=TargetTrakAuditEventCode.SEARCH_COMPANY_BY_CRITERIA)
	@Override
	public CompanyApiResponse processRequest(final CompanyApiRequest request) throws TargetTrakException {
		CompanyApiResponse response = new CompanyApiResponse();
		List<TargetTrakValidationError> validationErrors = validateRequest(request);

		if (!validationErrors.isEmpty()) {
			TargetTrakException exception = generateServiceException(response, validationErrors, TargetTrakErrorTypeEnum.VALIDATION, "A validation error has occurred. Please fix the errors below");
			throw exception;
		}

		try {
			CompanySearchCriteria criteria = conversionService.convert(request.getSearchCriteria(), CompanySearchCriteria.class);
			int totalSize = companyDao.selectPaginatedCompaniesCount(criteria);
			if (totalSize > 0) {
				List<CompanyDomain> companies = companyDao.selectPaginatedCompaniesByCriteria(criteria);
				List<CompanyDto> dtos = convertDomainList(companies);
				response.setCompanies(dtos);
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
	public List<TargetTrakValidationError> validateRequest(final CompanyApiRequest request) throws TargetTrakException {
		request.setRequestType(TargetTrakRequestTypeEnum.READ_PAGING);
		List<TargetTrakValidationError> errors = new ArrayList<TargetTrakValidationError>();
		try {
			errors = validator.validate(request);
		} catch (TargetTrakValidationException e) {
			logger.error("Validation error", e);
		}
		return errors;
	}

	private List<CompanyDto> convertDomainList(final List<CompanyDomain> companies) {
		List<CompanyDto> list = new ArrayList<>();
		if (companies != null && !companies.isEmpty()) {
			CompanyDto dto = null;
			for (CompanyDomain domain : companies) {
				dto = conversionService.convert(domain, CompanyDto.class);
				list.add(dto);
			}
		}
		return list;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setValidator(TargetTrakValidator<CompanyApiRequest> validator) {
		this.validator = validator;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
}