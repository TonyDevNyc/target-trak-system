package com.target.trak.system.dao.builder;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.target.trak.system.domain.criteria.ContactSearchCriteria;

public class ContactQueryBuilder {

	private static final String FIRST_NAME_COLUMN = "first_name";
	private static final String LAST_NAME_COLUMN = "last_name";
	private static final String COMPANY_COLUMN = "company_id";
	private static final String CONTACT_TYPE_COLUMN = "contact_type";
	private static final String DEFAULT_SORT_ORDER = " ORDER BY last_name ASC ";

	private Logger logger = Logger.getLogger(getClass());

	private Properties contactQueries;

	public String buildContactQueryByCriteriaCount(final ContactSearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		String baseCountSql = contactQueries.getProperty("baseSelectContactsByCriteriaCountSql");
		builder.append(baseCountSql);
		builder.append(buildCriteriaQuery(criteria, params));
		return builder.toString();
	}
	
	private String buildCriteriaQuery(final ContactSearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		if (!StringUtils.isEmpty(criteria.getFirstName())) {
			builder.append(QueryConstantsEnum.AND.value).append(FIRST_NAME_COLUMN).append(QueryConstantsEnum.EQUALS).append(":firstName");
			params.addValue("firstName", criteria.getFirstName());
		}

		if (!StringUtils.isEmpty(criteria.getLastName())) {
			builder.append(QueryConstantsEnum.AND.value).append(LAST_NAME_COLUMN).append(QueryConstantsEnum.EQUALS).append(":lastName");
			params.addValue("lastName", criteria.getLastName());
		}

		if (criteria.getCompanyId() != null && criteria.getCompanyId() != 0L) {
			builder.append(QueryConstantsEnum.AND.value).append(COMPANY_COLUMN).append(QueryConstantsEnum.EQUALS).append(":companyId");
			params.addValue("companyId", criteria.getCompanyId());
		}

		if (!StringUtils.isEmpty(criteria.getContactType())) {
			builder.append(QueryConstantsEnum.AND.value).append(CONTACT_TYPE_COLUMN).append(QueryConstantsEnum.EQUALS).append(":contactType");
			params.addValue("contactType", criteria.getContactType());
		}
		logger.info("Query built: " + builder.toString());
		return builder.toString();
	}

	public String buildPaginatedContactQueryByCriteria(final ContactSearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		String baseCountSql = contactQueries.getProperty("baseSelectContactsByCriteriaSql");
		builder.append(baseCountSql);
		builder.append(buildCriteriaQuery(criteria, params));
		
		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(QueryConstantsEnum.ORDER_BY_CLAUSE.value).append(":sortField").append(QueryConstantsEnum.EMPTY_SPACE.value).append(QueryConstantsEnum.ASCENDING_ORDER.value);
			params.addValue("sortField", criteria.getSortField());
		}

		builder.append(QueryConstantsEnum.LIMIT_CLAUSE.value).append(":pageStart").append(QueryConstantsEnum.COMMA.value).append(":pageEnd");
		params.addValue("pageStart", criteria.getStart());
		params.addValue("pageEnd", criteria.getEnd());
		logger.info("Query built: " + builder.toString());
		return builder.toString();
	}

	public void setContactQueries(Properties contactQueries) {
		this.contactQueries = contactQueries;
	}
}