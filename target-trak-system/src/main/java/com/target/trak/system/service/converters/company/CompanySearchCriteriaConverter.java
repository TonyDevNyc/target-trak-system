package com.target.trak.system.service.converters.company;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.criteria.CompanySearchCriteria;
import com.target.trak.system.service.dto.company.CompanySearchCriteriaDto;

public class CompanySearchCriteriaConverter implements Converter<CompanySearchCriteriaDto, CompanySearchCriteria> {

	@Override
	public CompanySearchCriteria convert(CompanySearchCriteriaDto dto) {
		CompanySearchCriteria searchCriteria = new CompanySearchCriteria();
		searchCriteria.setName(dto.getName());
		searchCriteria.setState(dto.getState());
		searchCriteria.setCountry(dto.getCountry());
		searchCriteria.setPage(dto.getPage());
		searchCriteria.setStart(dto.getStart());
		searchCriteria.setEnd(dto.getEnd());
		searchCriteria.setSortField(dto.getSortField());
		searchCriteria.setSortDirection(dto.getSortDirection());
		return searchCriteria;
	}

}
