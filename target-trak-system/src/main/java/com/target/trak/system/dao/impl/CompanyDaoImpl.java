package com.target.trak.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.target.trak.system.dao.CompanyDao;
import com.target.trak.system.dao.builder.CompanyQueryBuilder;
import com.target.trak.system.domain.CompanyDomain;
import com.target.trak.system.domain.criteria.CompanySearchCriteria;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	private final Logger logger = Logger.getLogger(getClass());

	@Qualifier("companyQueries")
	@Autowired
	private Properties companyQueries;

	@Qualifier("companyQueryBuilder")
	@Autowired
	private CompanyQueryBuilder companyQueryBuilder;

	private NamedParameterJdbcTemplate companyTemplate;

	@Autowired
	public CompanyDaoImpl(@Qualifier("dwDataSource") DataSource dataSource) {
		companyTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public CompanyDomain insertCompany(final CompanyDomain company) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", company.getName());
		params.addValue("addressLine1", company.getAddressLine1());
		params.addValue("addressLine2", company.getAddressLine2());
		params.addValue("city", company.getCity());
		params.addValue("state", company.getState());
		params.addValue("zipcode", company.getZipcode());
		params.addValue("country", company.getCountry());
		params.addValue("createdBy", company.getCreatedBy());
		params.addValue("createdTimestamp", company.getCreatedTimestamp());
		params.addValue("lastUpdatedBy", company.getLastUpdatedBy());
		params.addValue("lastUpdatedTimestamp", company.getLastUpdatedTimestamp());

		String sql = companyQueries.getProperty("insertCompanySql");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = companyTemplate.update(sql, params, keyHolder);

		if (count > 0) {
			company.setId(keyHolder.getKey().longValue());
		} else {
			logger.error("Company [" + company.getName() + "] was not inserted");
			throw new RuntimeException("Company [" + company.getName() + "] was not inserted");
		}
		return company;
	}

	@Override
	public CompanyDomain selectCompanyById(final CompanyDomain company) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", company.getId());
		String sql = companyQueries.getProperty("selectCompanyByIdSql");
		return companyTemplate.queryForObject(sql, params, new CompanyRowMapper());
	}

	@Override
	public List<CompanyDomain> selectPaginatedCompaniesByCriteria(final CompanySearchCriteria criteria) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = companyQueryBuilder.buildPaginatedQueryByCriteria(criteria, params);
		return companyTemplate.query(sql, params, new CompanyRowMapper());
	}

	@Override
	public int selectPaginatedCompaniesCount(final CompanySearchCriteria criteria) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = companyQueryBuilder.buildPagingQueryCountByCriteria(criteria, params);
		return companyTemplate.queryForObject(sql, params, Integer.class);
	}

	@Override
	public CompanyDomain updateCompany(final CompanyDomain company) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", company.getName());
		params.addValue("addressLine1", company.getAddressLine1());
		params.addValue("addressLine2", company.getAddressLine2());
		params.addValue("city", company.getCity());
		params.addValue("state", company.getState());
		params.addValue("zipcode", company.getZipcode());
		params.addValue("country", company.getCountry());
		params.addValue("lastUpdatedBy", company.getLastUpdatedBy());
		params.addValue("lastUpdatedTimestamp", company.getLastUpdatedTimestamp());
		params.addValue("id", company.getId());
		
		String sql = companyQueries.getProperty("updateCompanySql");
		int count = companyTemplate.update(sql, params);
		if (count != 1) {
			logger.error("Company was not updated");
			throw new RuntimeException("Company was not updated!");
		} else {
			logger.info("Company was updated successfully");
			return company;
		}
	}

	@Override
	public List<CompanyDomain> selectAllCompanyNames() {
		String sql = companyQueries.getProperty("selectDistinctCompaniesSql");
		return companyTemplate.query(sql, new ResultSetExtractor<List<CompanyDomain>>() {

			@Override
			public List<CompanyDomain> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<CompanyDomain> companies = new ArrayList<CompanyDomain>();
				CompanyDomain company = null;
				while (rs.next()) {
					company = new CompanyDomain();
					company.setId(rs.getLong("id"));
					company.setName(rs.getString("name"));
					companies.add(company);
				}
				return companies;
			}
		});
	}

	private final class CompanyRowMapper implements RowMapper<CompanyDomain> {

		@Override
		public CompanyDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
			CompanyDomain company = new CompanyDomain();
			company.setId(rs.getLong("id"));
			company.setName(rs.getString("name"));
			company.setAddressLine1(rs.getString("address_line1"));
			company.setAddressLine2(rs.getString("address_line2"));
			company.setCity(rs.getString("city"));
			company.setState(rs.getString("state"));
			company.setCountry(rs.getString("country"));
			company.setZipcode(rs.getString("zipcode"));
			company.setCreatedBy(rs.getString("created_by"));
			company.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
			company.setLastUpdatedBy(rs.getString("last_updated_by"));
			company.setLastUpdatedTimestamp(rs.getTimestamp("last_updated_timestamp"));
			return company;
		}
	}
}