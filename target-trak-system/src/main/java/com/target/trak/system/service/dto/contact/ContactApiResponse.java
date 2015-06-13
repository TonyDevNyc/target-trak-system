package com.target.trak.system.service.dto.contact;

import java.util.List;

import com.target.trak.system.service.dto.common.BaseTargetTrakApiResponse;

public class ContactApiResponse extends BaseTargetTrakApiResponse {

	private ContactDto contact;
	private List<ContactDto> contacts;

	public ContactDto getContact() {
		return contact;
	}

	public void setContact(ContactDto contact) {
		this.contact = contact;
	}

	public List<ContactDto> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactDto> contacts) {
		this.contacts = contacts;
	}

}
