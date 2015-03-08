package com.target.trak.system.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.service.dto.referencedata.ReferenceDataDto;

public class ReferenceDataDtoDomainConverter implements Converter<ReferenceDataDto, ReferenceDataDomain>{

	@Override
	public ReferenceDataDomain convert(ReferenceDataDto dto) {
		return null;
	}

}
