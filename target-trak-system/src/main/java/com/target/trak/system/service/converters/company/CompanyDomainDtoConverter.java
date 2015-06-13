package com.target.trak.system.service.converters.company;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.util.DateUtil;

public class CompanyDomainDtoConverter implements Converter<CompanyDomain, CompanyDto> {

	@Override
	public CompanyDto convert(CompanyDomain domain) {
		CompanyDto dto = new CompanyDto();
		dto.setId(domain.getId());
		dto.setName(domain.getName());
		dto.setAddressLine1(domain.getAddressLine1());
		dto.setAddressLine2(domain.getAddressLine2());
		dto.setCity(domain.getCity());
		dto.setState(domain.getState());
		dto.setCountry(domain.getCountry());
		dto.setZipcode(domain.getZipcode());
		dto.setCreatedBy(domain.getCreatedBy());
		dto.setCreateDateTime(DateUtil.convertTimestampToCalendar(domain.getCreatedTimestamp()));
		dto.setLastUpdatedBy(domain.getLastUpdatedBy());
		dto.setLastUpdatedDateTime(DateUtil.convertTimestampToCalendar(domain.getLastUpdatedTimestamp()));
		return dto;
	}

}
