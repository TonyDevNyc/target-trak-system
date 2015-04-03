package com.target.trak.system.service.dto.company;

import java.util.List;

import com.target.trak.system.service.dto.common.AbstractTargetTrakApiResponse;

public class CompanyApiResponse extends AbstractTargetTrakApiResponse {

	private CompanyDto company;
	private List<CompanyDto> companies;

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public List<CompanyDto> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyDto> companies) {
		this.companies = companies;
	}

}
