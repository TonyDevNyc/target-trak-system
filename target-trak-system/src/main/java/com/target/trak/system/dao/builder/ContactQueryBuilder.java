package com.target.trak.system.dao.builder;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.target.trak.system.domain.criteria.ContactSearchCriteria;

public class ContactQueryBuilder {

	private static final String FIRST_NAME_COLUMN = "first_name";
	private static final String LAST_NAME_COLUMN = "last_name";
	private static final String COMPANY_COLUMN = "company_id";
	private static final String CONTACT_TYPE_COLUMN = "contact_type";

	private Logger logger = Logger.getLogger(getClass());

	private Properties contactQueries;

	public String buildContactQueryByCriteriaCount(final ContactSearchCriteria criteria, final MapSqlParameterSource params) {

		return null;
	}

	public String buildPaginatedContactQueryByCriteria(final ContactSearchCriteria criteria, final MapSqlParameterSource params) {

		return null;
	}
}
