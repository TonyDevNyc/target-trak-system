package com.target.trak.system.dao;

import java.util.List;

import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.domain.criteria.ReferenceDataSearchCriteria;

public interface ReferenceDataDao {

	public ReferenceDataDomain insertReferenceData(final ReferenceDataDomain referenceData);
	
	public List<ReferenceDataDomain> selectDefaultPaginatedReferenceData(final ReferenceDataSearchCriteria criteria);
	
	public int selectDefaultReferenceDataCount(final ReferenceDataSearchCriteria criteria);
	
	public List<ReferenceDataDomain> selectPaginatedReferenceDataBySearchCriteria(final ReferenceDataSearchCriteria criteria);
	
	public int selectReferenceDataBySearchCriteriaCount(final ReferenceDataSearchCriteria criteria);
	
	public boolean referenceDataAlreadyExists(final String type, final String label, final String value);
	
	public List<ReferenceDataDomain> getReferenceDataTypes();
	
	public ReferenceDataDomain updateReferenceData(final ReferenceDataDomain referenceData);
	
	public void deleteReferenceData(final ReferenceDataDomain referenceData);
	
	public List<ReferenceDataDomain> selectReferenceDataByType(final String referenceDataType);
	
	public ReferenceDataDomain selectReferenceDataByFields(final String type, final String label, final String value);
}
