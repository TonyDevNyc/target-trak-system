package com.target.trak.system.dao;

import java.util.List;

import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.domain.ReferenceDataSearchCriteria;

public interface ReferenceDataDao {

	public ReferenceDataDomain insertReferenceData(final ReferenceDataDomain referenceData);
	
	public List<ReferenceDataDomain> selectDefaultPaginatedReferenceData(final ReferenceDataSearchCriteria criteria);
	
	public int selectDefaultReferenceDataCount(final ReferenceDataSearchCriteria criteria);
	
	public List<ReferenceDataDomain> selectPaginatedReferenceDataBySearchCriteria(final ReferenceDataSearchCriteria criteria);
	
	public int selectReferenceDataBySearchCriteriaCount(final ReferenceDataSearchCriteria criteria);
	
	public boolean referenceDataAlreadyExists(final String type, final String label, final String value);
	
	public List<ReferenceDataDomain> getReferenceDataTypes();
}
