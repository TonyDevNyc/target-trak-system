package com.target.trak.system.dao;

import java.util.List;

import com.target.trak.system.domain.ContactDomain;
import com.target.trak.system.domain.criteria.ContactSearchCriteria;

public interface ContactDao {

	public ContactDomain insertContact(final ContactDomain contact);
	
	public ContactDomain selectContactById(final Long id);
	
	public List<ContactDomain> selectContactsByCriteria(final ContactSearchCriteria criteria);
	
	public ContactDomain updateContact(final ContactDomain contact);
	
	public int selectContactsByCriteriaCount(final ContactSearchCriteria criteria);
}
