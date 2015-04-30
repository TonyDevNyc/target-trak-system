package com.target.trak.system.service.converters.referencedata;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;

public class ReferenceDataDtoDomainConverter implements Converter<ReferenceDataDto, ReferenceDataDomain> {

	@Override
	public ReferenceDataDomain convert(ReferenceDataDto dto) {
		ReferenceDataDomain domain = new ReferenceDataDomain();
		domain.setId(dto.getId());
		domain.setReferenceDataType(dto.getType());
		domain.setReferenceDataLabel(dto.getLabel());
		domain.setReferenceDataValue(dto.getValue());
		domain.setCreatedBy(dto.getCreatedBy());
		domain.setStatus(dto.getStatus());
		
		if (dto.getCreatedDateTime() != null) {
			Timestamp createTimestamp = new Timestamp(dto.getCreatedDateTime().getTimeInMillis());
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
