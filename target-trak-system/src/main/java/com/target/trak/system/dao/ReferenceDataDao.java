package com.target.trak.system.dao;

import java.util.List;

import com.target.trak.system.domain.ReferenceDataDomain;

public interface ReferenceDataDao {

	public ReferenceDataDomain insertReferenceData(final ReferenceDataDomain referenceData);
	
	public List<ReferenceDataDomain> getAllPaginatedReferenceData();
	
	public int getAllReferenceDataCount();
}
