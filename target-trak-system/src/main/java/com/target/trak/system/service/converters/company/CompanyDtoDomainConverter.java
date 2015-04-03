package com.target.trak.system.service.converters.company;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.service.dto.company.CompanyDto;

public class CompanyDtoDomainConverter implements Converter<CompanyDto, CompanyDomain> {

	@Override
	public CompanyDomain convert(CompanyDto dto) {
		CompanyDomain domain = new CompanyDomain();
		domain.setId(dto.getId());
		domain.setName(dto.getName());
		domain.setAddressLine1(dto.getAddressLine1());
		domain.setAddressLine2(dto.getAddressLine2());
		domain.setCity(dto.getCity());
		domain.setState(dto.getState());
		domain.setCountry(dto.getCountry());
		domain.setZipcode(dto.getZipcode());
		domain.setCreatedBy(dto.getCreatedBy());
		
		if (dto.getCreateDateTime() != null) {
			Timestamp createTimestamp = new Timestamp(dto.getCreateDateTime().getTimeInMillis());
			domain.setCreatedTimestamp(createTimestamp);
		}
		domain.setLastUpdatedBy(dto.getLastUpdatedBy());
		
		if (dto.getLastUpdatedDateTime() != null) {
			Timestamp lastUpdateTimestamp = new Timestamp(dto.getLastUpdatedDateTime().getTimeInMillis());
			domain.setLastUpdatedTimestamp(lastUpdateTimestamp);
		}
		return domain;
	}

}
