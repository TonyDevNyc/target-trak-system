package com.target.trak.system.service.converters.contact;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.domain.ContactDomain;
import com.target.trak.system.service.dto.company.CompanyDto;
import com.target.trak.system.service.dto.contact.ContactDto;
import com.target.trak.system.util.DateUtil;

public class ContactDomainToDtoConverter implements Converter<ContactDomain, ContactDto> {

	@Override
	public ContactDto convert(ContactDomain domain) {
		ContactDto dto = new ContactDto();
		dto.setId(domain.getId());
		CompanyDto company = new CompanyDto();
		company.setId(domain.getCompany().getId());
		company.setName(domain.getCompany().getName());
		dto.setCompany(company);
		dto.setActiveAtCompany(domain.isActiveAtCompany());
		dto.setContactType(domain.getContactType());
		dto.setCreatedBy(domain.getCreatedBy());
		dto.setCreatedDate(DateUtil.convertTimestampToCalendar(domain.getCreatedTimestamp()));
		dto.setEmailAddress(domain.getEmailAddress());
		dto.setFirstName(domain.getFirstName());
		dto.setLastName(domain.getLastName());
		dto.setLastUpdatedBy(domain.getLastUpdatedBy());
		dto.setLastUpdatedDate(DateUtil.convertTimestampToCalendar(domain.getLastUpdatedTimestamp()));
		dto.setMiddleInitial(domain.getMiddleInitial());
		dto.setSuffix(domain.getSuffix());
		dto.setTelephoneNumber(domain.getTelephoneNumber());
		dto.setTitle(domain.getTitle());
		return dto;
	}

}
