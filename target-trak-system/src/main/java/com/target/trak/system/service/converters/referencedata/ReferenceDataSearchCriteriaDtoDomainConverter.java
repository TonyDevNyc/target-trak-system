package com.target.trak.system.service.converters.referencedata;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.criteria.ReferenceDataSearchCriteria;
import com.target.trak.system.service.dto.referencedata.ReferenceDataSearchCriteriaDto;

public class ReferenceDataSearchCriteriaDtoDomainConverter implements Converter<ReferenceDataSearchCriteriaDto, ReferenceDataSearchCriteria> {

	@Override
	public ReferenceDataSearchCriteria convert(ReferenceDataSearchCriteriaDto searchCriteriaDto) {
		ReferenceDataSearchCriteria criteria = new ReferenceDataSearchCriteria();
		criteria.setReferenceDataType(searchCriteriaDto.getReferenceDataType());
		criteria.setReferenceDataLabel(searchCriteriaDto.getReferenceDataLabel());
		criteria.setPage(searchCriteriaDto.getPage());
		criteria.setStart(searchCriteriaDto.getStart());
		criteria.setEnd(searchCriteriaDto.getEnd());
		criteria.setSortDirection(searchCriteriaDto.getSortDirection());
		criteria.setSortField(searchCriteriaDto.getSortField());
		return criteria;
	}

}
