package com.target.trak.system.service.converters.referencedata;

import java.util.Calendar;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;

public class ReferenceDataDomainDtoConverter implements Converter<ReferenceDataDomain, ReferenceDataDto> {

	@Override
	public ReferenceDataDto convert(final ReferenceDataDomain domain) {
		ReferenceDataDto dto = new ReferenceDataDto();
		dto.setId(domain.getId());
		dto.setType(domain.getReferenceDataType());
		dto.setLabel(domain.getReferenceDataLabel());
		dto.setValue(domain.getReferenceDataValue());
		dto.setCreatedBy(domain.getCreatedBy());
		dto.setStatus(domain.getStatus());
		
		if (domain.getCreatedTimestamp() == null) {
			dto.setCreatedDateTime(null);
		} else {
			Calendar createDateTime = Calendar.getInstance();
			createDateTime.setTimeInMillis(domain.getCreatedTimestamp().getTime());
			dto.setCreatedDateTime(createDateTime);
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