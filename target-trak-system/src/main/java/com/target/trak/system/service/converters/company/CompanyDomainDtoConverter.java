package com.target.trak.system.service.converters.company;

import java.util.Calendar;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.service.dto.company.CompanyDto;

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
		dto.setZipcode(dto.getZipcode());
		dto.setCreatedBy(domain.getCreatedBy());

		if (domain.getCreatedTimestamp() == null) {
			dto.setCreateDateTime(null);
		} else {
			Calendar createDateTime = Calendar.getInstance();
			createDateTime.setTimeInMillis(domain.getCreatedTimestamp().getTime());
			dto.setCreateDateTime(createDateTime);
		}

		dto.setLastUpdatedBy(domain.getLastUpdatedBy());
		if (domain.getLastUpdatedTimestamp() == null) {
			dto.setLastUpdatedDateTime(null);
		} else {
			Calendar lastUpdateDateTime = Calendar.getInstance();
			lastUpdateDateTime.setTimeInMillis(domain.getLastUpdatedTimestamp().getTime());
			dto.setLastUpdatedDateTime(lastUpdateDateTime);
		}

		return dto;
	}

}
