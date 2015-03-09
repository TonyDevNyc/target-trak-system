package com.target.trak.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.service.ReferenceDataService;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiRequest;
import com.target.trak.system.service.dto.referencedata.ReferenceDataApiResponse;
import com.target.trak.system.service.exception.TargetTrakException;

@Transactional(value="dwTransactionManager", propagation=Propagation.REQUIRED, rollbackFor=TargetTrakException.class)
@Service("referenceDataService")
public class ReferenceDataServiceImpl implements ReferenceDataService {

	@Override
	public ReferenceDataApiResponse createReferenceData(final ReferenceDataApiRequest request) throws TargetTrakException {
		
		return null;
	}

	@Transactional(propagation=Propagation.NEVER)
	@Override
	public ReferenceDataApiResponse getReferenceDataByCriteria(final ReferenceDataApiRequest request) throws TargetTrakException {
		
		return null;
	}

}
