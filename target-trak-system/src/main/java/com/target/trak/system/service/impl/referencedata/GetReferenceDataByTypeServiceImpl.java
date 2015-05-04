package com.target.trak.system.service.impl.referencedata;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.convert.ConversionService;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;

public class GetReferenceDataByTypeServiceImpl extends BaseTargetTrakService implements TargetTrakService<ReferenceDataApiRequest, ReferenceDataApiResponse> {

	private final Logger logger = Logger.getLogger(getClass());

	private ReferenceDataDao referenceDataDao;

	private ConversionService conversionService;

	@Override
	public ReferenceDataApiResponse executeRequest(final ReferenceDataApiRequest request) throws TargetTrakException {
		ReferenceDataApiResponse response = new ReferenceDataApiResponse();
		String referenceDataType = request.getReferenceDataDto().getType();
		logger.info("Getting reference data by type: "+referenceDataType);
		List<ReferenceDataDomain> list = referenceDataDao.selectReferenceDataByType(referenceDataType);
		logger.info("Reference Data by " +referenceDataType+" type returned [" + list.size() + "] items" );
		response.setReferenceDataList(convertListOfDomains(list));
		response.setSuccess(Boolean.TRUE);
		return response;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(final ReferenceDataApiRequest request) throws TargetTrakException {

		return null;
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
}
