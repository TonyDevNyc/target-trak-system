package com.target.trak.system.dao.builder;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.StringUtils;

import com.target.trak.system.domain.criteria.ReferenceDataSearchCriteria;

public class ReferenceDataQueryBuilder {

	private static final String DEFAULT_SORT_ORDER = " ORDER BY reference_data_type, reference_data_label ASC ";
	private static final String REFERENCE_DATA_TYPE_COLUMN = "reference_data_type";
	private static final String REFERENCE_DATA_LABEL_COLUMN = "reference_data_label";
	private static final String STATUS_COLUMN = "status";

	private Logger logger = Logger.getLogger(getClass());

	private Properties referenceDataQueries;

	public String buildDefaultReferenceDataPaginatedQuery(final ReferenceDataSearchCriteria criteria) {
		StringBuilder builder = new StringBuilder();
		builder.append(referenceDataQueries.get("baseReferenceDataPagingSql"));

		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(QueryConstantsEnum.ORDER_BY_CLAUSE.value).append(criteria.getSortField()).append(QueryConstantsEnum.EMPTY_SPACE.value).append(QueryConstantsEnum.DESCENDING_ORDER.value);
		}
		builder.append(QueryConstantsEnum.LIMIT_CLAUSE.value).append(criteria.getStart()).append(QueryConstantsEnum.COMMA.value).append(criteria.getEnd());

		logger.info("Default Reference Data Query Built: " + builder.toString());
		return builder.toString();
	}

	public String buildSearchCriteriaReferenceDataPaginatedQuery(final ReferenceDataSearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		builder.append(referenceDataQueries.get("baseReferenceDataPagingSql"));
		builder.append(QueryConstantsEnum.WHERE_CLAUSE.value).append(QueryConstantsEnum.EMPTY_SPACE.value);

		if (!StringUtils.isEmpty(criteria.getReferenceDataType())) {
			builder.append(QueryConstantsEnum.AND.value).append(REFERENCE_DATA_TYPE_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":referenceDataType");
			params.addValue("referenceDataType", criteria.getReferenceDataType());
		}

		if (!StringUtils.isEmpty(criteria.getReferenceDataLabel())) {
			builder.append(QueryConstantsEnum.AND.value).append(REFERENCE_DATA_LABEL_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":referenceDataLabel");
			params.addValue("referenceDataLabel", criteria.getReferenceDataLabel());
		}
		
		if (!StringUtils.isEmpty(criteria.getStatus())) {
			builder.append(QueryConstantsEnum.AND.value).append(STATUS_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":status");
			params.addValue("status", criteria.getStatus());
		}

		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(QueryConstantsEnum.ORDER_BY_CLAUSE.value).append(":sortField").append(QueryConstantsEnum.EMPTY_SPACE.value).append(QueryConstantsEnum.DESCENDING_ORDER.value);
			params.addValue("sortField", criteria.getSortField());
		}

		builder.append(QueryConstantsEnum.LIMIT_CLAUSE.value).append(":pageStart").append(QueryConstantsEnum.COMMA.value).append(":pageEnd");
		params.addValue("pageStart", criteria.getStart());
		params.addValue("pageEnd", criteria.getEnd());

		logger.info("Reference Data Query Built: " + builder.toString());
		return builder.toString();
	}

	public String buildSearchCriteriaReferenceDataCountQuery(final ReferenceDataSearchCriteria criteria, final MapSqlParameterSource params) {
		StringBuilder builder = new StringBuilder();
		builder.append(referenceDataQueries.get("selectCountOfAllReferenceDataSql"));
		builder.append(QueryConstantsEnum.WHERE_CLAUSE.value).append(QueryConstantsEnum.EMPTY_SPACE.value);

		if (!StringUtils.isEmpty(criteria.getReferenceDataType())) {
			builder.append(QueryConstantsEnum.AND.value).append(REFERENCE_DATA_TYPE_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":referenceDataType");
			params.addValue("referenceDataType", criteria.getReferenceDataType());
		}

		if (!StringUtils.isEmpty(criteria.getReferenceDataLabel())) {
			builder.append(QueryConstantsEnum.AND.value).append(REFERENCE_DATA_LABEL_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":referenceDataLabel");
			params.addValue("referenceDataLabel", criteria.getReferenceDataLabel());
		}
		
		if (!StringUtils.isEmpty(criteria.getStatus())) {
			builder.append(QueryConstantsEnum.AND.value).append(STATUS_COLUMN).append(QueryConstantsEnum.EQUALS.value).append(":status");
			params.addValue("status", criteria.getStatus());
		}

		if (StringUtils.isEmpty(criteria.getSortField())) {
			builder.append(DEFAULT_SORT_ORDER);
		} else {
			builder.append(QueryConstantsEnum.ORDER_BY_CLAUSE.value).append(":sortField").append(QueryConstantsEnum.EMPTY_SPACE.value).append(QueryConstantsEnum.DESCENDING_ORDER.value);
			params.addValue("sortField", criteria.getSortField());
		}
		logger.info("Reference Data Count Query Built: " + builder.toString());
		return builder.toString();
	}

	public void setReferenceDataQueries(Properties referenceDataQueries) {
		this.referenceDataQueries = referenceDataQueries;
	}
}