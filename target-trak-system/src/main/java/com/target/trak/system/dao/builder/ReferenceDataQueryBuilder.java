package com.target.trak.system.dao.builder;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.domain.ReferenceDataSearchCriteria;

@Component("referenceDataQueryBuilder")
public class ReferenceDataQueryBuilder {

	private static final String WHERE_CLAUSE = " WHERE 1 = 1";
	private static final String AND = " AND ";
	private static final String EQUALS = " = ";
	private static final String LIKE = " LIKE ";
	private static final String WILDCARD = "% ";
	private static final String ORDER_BY_CLAUSE = " ORDER BY ";
	private static final String ASCENDING_ORDER = " ASC ";
	private static final String DESCENDING_ORDER = " DESC ";
	private static final String LIMIT_CLAUSE = " LIMIT ";
	private static final String COMMA = ",";
	private static final String DEFAULT_SORT_ORDER = " ORDER BY reference_data_type, reference_data_label ASC ";
	private static final String EMPTY_SPACE = " ";
	private static final String REFERENCE_DATA_TYPE_COLUMN = "reference_data_type";
	private static final String REFERENCE_DATA_LABEL_COLUMN = "reference_data_label";

	@Qualifier("referenceDataQueries")
	@Autowired
	private Properties referenceDataQueries;

	private Logger logger = Logger.getLogger(getClass());

	public String buildDefaultReferenceDataPaginatedQuery(final ReferenceDataSearchCriteria criteria) {
		StringBuilder builder = new StringBuilder();
		builder.append(referenceDataQueries.get("baseCompaniesPagingQuery"));

		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(ORDER_BY_CLAUSE).append(criteria.getSortField()).append(EMPTY_SPACE).append(DESCENDING_ORDER);
		}
		builder.append(LIMIT_CLAUSE).append(criteria.getStart()).append(COMMA).append(criteria.getEnd());

		logger.info("Default Reference Data Query Built: " + builder.toString());
		return builder.toString();
	}

	public String buildSearchCriteriaReferenceDataPaginatedQuery(final ReferenceDataSearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		builder.append(referenceDataQueries.get("baseCompaniesPagingQuery"));
		builder.append(WHERE_CLAUSE).append(EMPTY_SPACE);

		if (!StringUtils.isEmpty(criteria.getReferenceDataType())) {
			builder.append(AND).append(REFERENCE_DATA_TYPE_COLUMN).append(EQUALS).append(":referenceDataType");
			params.addValue("referenceDataType", criteria.getReferenceDataType());
		}

		if (!StringUtils.isEmpty(criteria.getReferenceDataLabel())) {
			builder.append(AND).append(REFERENCE_DATA_LABEL_COLUMN).append(EQUALS).append(":referenceDataLabel");
			params.addValue("referenceDataLabel", criteria.getReferenceDataLabel());
		}

		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(ORDER_BY_CLAUSE).append(":sortField").append(EMPTY_SPACE).append(DESCENDING_ORDER);
			params.addValue("sortField", criteria.getSortField());
		}

		builder.append(LIMIT_CLAUSE).append(":pageStart").append(COMMA).append(":pageEnd");
		params.addValue("pageStart", criteria.getStart());
		params.addValue("pageEnd", criteria.getEnd());

		logger.info("Reference Data Query Built: " + builder.toString());
		return builder.toString();
	}

	public String buildSearchCriteriaReferenceDataCountQuery(final ReferenceDataSearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		builder.append(referenceDataQueries.get("selectCountOfAllReferenceDataSql"));
		builder.append(WHERE_CLAUSE).append(EMPTY_SPACE);

		if (!StringUtils.isEmpty(criteria.getReferenceDataType())) {
			builder.append(AND).append(REFERENCE_DATA_TYPE_COLUMN).append(EQUALS).append(":referenceDataType");
			params.addValue("referenceDataType", criteria.getReferenceDataType());
		}

		if (!StringUtils.isEmpty(criteria.getReferenceDataLabel())) {
			builder.append(AND).append(REFERENCE_DATA_LABEL_COLUMN).append(EQUALS).append(":referenceDataLabel");
			params.addValue("referenceDataLabel", criteria.getReferenceDataLabel());
		}

		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(ORDER_BY_CLAUSE).append(":sortField").append(EMPTY_SPACE).append(DESCENDING_ORDER);
			params.addValue("sortField", criteria.getSortField());
		}
		logger.info("Reference Data Count Query Built: " + builder.toString());
		return builder.toString();
	}
}