package com.target.trak.system.dao;

import java.util.List;

import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.domain.criteria.CompanySearchCriteria;

public interface CompanyDao {

	public CompanyDomain insertCompany(final CompanyDomain company);

	public CompanyDomain selectCompanyById(final CompanyDomain company);

	public List<CompanyDomain> selectPaginatedCompaniesByCriteria(final CompanySearchCriteria criteria);

	public int selectPaginatedCompaniesCount(final CompanySearchCriteria criteria);

	public CompanyDomain updateCompany(final CompanyDomain company);
	
	public List<CompanyDomain> selectAllCompanyNames();

}
