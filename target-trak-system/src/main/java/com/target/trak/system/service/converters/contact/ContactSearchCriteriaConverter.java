package com.target.trak.system.service.converters.contact;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.criteria.ContactSearchCriteria;
import com.target.trak.system.service.dto.contact.ContactSearchCriteriaDto;

public class ContactSearchCriteriaConverter implements Converter<ContactSearchCriteriaDto, ContactSearchCriteria> {

	@Override
	public ContactSearchCriteria convert(ContactSearchCriteriaDto dto) {
		ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setCompanyId(dto.getCompanyId());
		criteria.setContactType(dto.getContactType());
		criteria.setFirstName(dto.getFirstName());
		criteria.setLastName(dto.getLastName());
		return null;
	}

}
