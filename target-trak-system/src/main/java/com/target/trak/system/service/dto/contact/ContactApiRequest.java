package com.target.trak.system.service.dto.contact;

import com.target.trak.system.service.dto.common.BaseTargetTrakApiRequest;

public class ContactApiRequest extends BaseTargetTrakApiRequest {

	private ContactDto contact;
	private ContactSearchCriteriaDto contactSearchCriteria;

	public ContactDto getContact() {
		return contact;
	}

	public void setContact(ContactDto contact) {
		this.contact = contact;
	}

	public ContactSearchCriteriaDto getContactSearchCriteria() {
		return contactSearchCriteria;
	}

	public void setContactSearchCriteria(ContactSearchCriteriaDto contactSearchCriteria) {
		this.contactSearchCriteria = contactSearchCriteria;
	}

}
