package com.target.trak.system.service.impl.company;

import java.util.List;

import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.dto.company.CompanyApiRequest;
import com.target.trak.system.service.dto.company.CompanyApiResponse;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;

public class GetCompaniesByCriteriaServiceImpl implements TargetTrakService<CompanyApiRequest, CompanyApiResponse> {

	@Override
	public CompanyApiResponse executeRequest(CompanyApiRequest request) throws TargetTrakException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TargetTrakValidationError> validateRequest(CompanyApiRequest request) throws TargetTrakException {
		// TODO Auto-generated method stub
		return null;
	}

}
