package com.target.trak.system.service.dto.company;

import com.target.trak.system.service.dto.common.BaseTargetTrakApiRequest;

public class CompanyApiRequest extends BaseTargetTrakApiRequest {

	private CompanyDto company;
	private CompanySearchCriteriaDto searchCriteria;

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public CompanySearchCriteriaDto getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(CompanySearchCriteriaDto searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

}
