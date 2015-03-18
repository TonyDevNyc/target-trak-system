package com.target.trak.system.service;

import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.exception.TargetTrakException;

public interface ReferenceDataService {

	public ReferenceDataApiResponse createReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException;
	
	public ReferenceDataApiResponse getReferenceDataByCriteria(final ReferenceDataApiRequest request) throws TargetTrakException;
	
	public ReferenceDataApiResponse getReferenceDataTypes();
	
	public ReferenceDataApiResponse updateReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException;
	
	public ReferenceDataApiResponse deleteReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException;
}
