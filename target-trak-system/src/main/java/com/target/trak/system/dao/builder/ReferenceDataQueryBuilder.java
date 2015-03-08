package com.target.trak.system.dao.builder;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.target.trak.system.domain.ReferenceDataSearchCriteria;

@Component("referenceDataQueryBuilder")
public class ReferenceDataQueryBuilder {

	private static final String WHERE_CLAUSE = " WHERE ";
	private static final String AND = " AND ";
	private static final String LIKE = " LIKE ";
	private static final String WILDCARD = "% ";
	private static final String ORDER_BY_CLAUSE = " ORDER BY ";
	private static final String ASCENDING_ORDER = " ASC ";
	private static final String DESCENDING_ORDER = " DESC ";
	private static final String LIMIT_CLAUSE = " LIMIT ";
	private static final String COMMA = ",";
	private static final String DEFAULT_SORT_ORDER = " ORDER BY reference_data_type, reference_data_label ASC ";
	private static final String EMPTY_SPACE = " ";

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

		logger.info("Query Built: " + builder.toString());
		return builder.toString();
	}
}
