package com.target.trak.system.service.converters.contact;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.domain.ContactDomain;
import com.target.trak.system.service.dto.contact.ContactDto;
import com.target.trak.system.util.DateUtil;

public class ContactDtoToDomainConverter implements Converter<ContactDto, ContactDomain> {

	@Override
	public ContactDomain convert(ContactDto dto) {
		ContactDomain domain = new ContactDomain();
		domain.setId(dto.getId());
		CompanyDomain company = new CompanyDomain();
		company.setId(dto.getCompany().getId());
		domain.setCompany(company);
		domain.setActiveAtCompany(domain.isActiveAtCompany());
		domain.setContactType(dto.getContactType());
		domain.setCreatedBy(dto.getCreatedBy());
		domain.setCreatedTimestamp(DateUtil.convertCalendarToTimestamp(dto.getCreatedDate()));
		domain.setEmailAddress(dto.getEmailAddress());
		domain.setFirstName(dto.getFirstName());
		domain.setLastName(dto.getLastName());
		domain.setLastUpdatedBy(dto.getLastUpdatedBy());
		domain.setLastUpdatedTimestamp(DateUtil.convertCalendarToTimestamp(dto.getLastUpdatedDate()));
		domain.setMiddleInitial(dto.getMiddleInitial());
		domain.setSuffix(dto.getSuffix());
		domain.setTelephoneNumber(dto.getTelephoneNumber());
		domain.setTitle(dto.getTitle());
		return domain;
	}

}
