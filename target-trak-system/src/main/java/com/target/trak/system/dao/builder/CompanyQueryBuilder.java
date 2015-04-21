package com.target.trak.system.dao.builder;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.StringUtils;

import com.target.trak.system.domain.criteria.CompanySearchCriteria;

public class CompanyQueryBuilder {

	private static final String NAME_COLUMN = "name";
	private static final String STATE_COLUMN = "state";
	private static final String COUNTRY_COLUMN = "country";
	private static final String DEFAULT_SORT_ORDER = " ORDER BY name ASC ";

	private Logger logger = Logger.getLogger(getClass());

	private Properties companyQueries;

	public String buildPagingQueryCountByCriteria(final CompanySearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		builder.append(companyQueries.get("selectBaseCountReferenceDataSql"));
		builder.append(QueryConstantsEnum.WHERE_CLAUSE.value).append(QueryConstantsEnum.EMPTY_SPACE.value);

		if (!StringUtils.isEmpty(criteria.getName())) {
			builder.append(QueryConstantsEnum.AND.value).append(NAME_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":name");
			params.addValue("name", criteria.getName());
		}

		if (!StringUtils.isEmpty(criteria.getState())) {
			builder.append(QueryConstantsEnum.AND.value).append(STATE_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":state");
			params.addValue("state", criteria.getState());
		}

		if (!StringUtils.isEmpty(criteria.getCountry())) {
			builder.append(QueryConstantsEnum.AND.value).append(COUNTRY_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":country");
			params.addValue("country", criteria.getCountry());
		}

		logger.info("Company Paging Count Query Built: " + builder.toString());
		return builder.toString();
	}

	public String buildPaginatedQueryByCriteria(final CompanySearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		builder.append(companyQueries.get("baseCompanySql"));
		builder.append(QueryConstantsEnum.WHERE_CLAUSE.value).append(QueryConstantsEnum.EMPTY_SPACE.value);

		if (!StringUtils.isEmpty(criteria.getName())) {
			builder.append(QueryConstantsEnum.AND.value).append(NAME_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":name");
			params.addValue("name", criteria.getName());
		}

		if (!StringUtils.isEmpty(criteria.getState())) {
			builder.append(QueryConstantsEnum.AND.value).append(STATE_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":state");
			params.addValue("state", criteria.getState());
		}

		if (!StringUtils.isEmpty(criteria.getCountry())) {
			builder.append(QueryConstantsEnum.AND.value).append(COUNTRY_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":country");
			params.addValue("country", criteria.getCountry());
		}

		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(QueryConstantsEnum.ORDER_BY_CLAUSE.value).append(":sortField").append(QueryConstantsEnum.EMPTY_SPACE.value).append(QueryConstantsEnum.ASCENDING_ORDER.value);
			params.addValue("sortField", criteria.getSortField());
		}

		builder.append(QueryConstantsEnum.LIMIT_CLAUSE.value).append(":pageStart").append(QueryConstantsEnum.COMMA.value).append(":pageEnd");
		params.addValue("pageStart", criteria.getStart());
		params.addValue("pageEnd", criteria.getEnd());

		logger.info("Company Paging Query Built: " + builder.toString());
		return builder.toString();
	}

	public void setCompanyQueries(Properties companyQueries) {
		this.companyQueries = companyQueries;
	}
}