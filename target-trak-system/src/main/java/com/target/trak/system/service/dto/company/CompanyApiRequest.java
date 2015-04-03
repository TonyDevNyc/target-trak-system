package com.target.trak.system.service.dto.company;

import com.target.trak.system.service.dto.common.AbstractTargetTrakApiRequest;

public class CompanyApiRequest extends AbstractTargetTrakApiRequest {

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
