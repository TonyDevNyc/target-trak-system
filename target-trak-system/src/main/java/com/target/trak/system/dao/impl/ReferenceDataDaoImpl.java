package com.target.trak.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.dao.builder.ReferenceDataQueryBuilder;
import com.target.trak.system.domain.ReferenceDataDomain;
import com.target.trak.system.domain.ReferenceDataSearchCriteria;

@Repository
public class ReferenceDataDaoImpl implements ReferenceDataDao {

	@Qualifier("referenceDataQueries")
	@Autowired
	private Properties referenceDataQueries;
	
	@Qualifier("referenceDataQueryBuilder")
	@Autowired
	private ReferenceDataQueryBuilder refDataQueryBuilder;

	private NamedParameterJdbcTemplate refDataTemplate;
	
	@Autowired
	public ReferenceDataDaoImpl(@Qualifier("dwDataSource") DataSource dataSource) {
		refDataTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public ReferenceDataDomain insertReferenceData(final ReferenceDataDomain referenceData) {
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue("referenceDataType", referenceData.getReferenceDataType());
		params.addValue("referenceDataLabel", referenceData.getReferenceDataLabel());
		params.addValue("referenceDataValue", referenceData.getReferenceDataValue());
		params.addValue("createdBy", referenceData.getCreatedBy());
		params.addValue("createdTimestamp", referenceData.getCreatedTimestamp());
		params.addValue("lastUpdatedBy", referenceData.getLastUpdatedBy());
		params.addValue("lastUpdatedTimestamp", referenceData.getLastUpdatedTimestamp());

		String sql = referenceDataQueries.getProperty("insertReferenceDataSql");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = refDataTemplate.update(sql, params, keyHolder);
		
		if (count > 0) {
			referenceData.setId(keyHolder.getKey().longValue());
		} else {
			throw new RuntimeException("Reference Data was not created");
		}
		return referenceData;
	}

	@Override
	public List<ReferenceDataDomain> selectDefaultPaginatedReferenceData(final ReferenceDataSearchCriteria criteria) {
		String sql = refDataQueryBuilder.buildDefaultReferenceDataPaginatedQuery(criteria);
		//refDataTemplate.query(sql, paramSource, rowMapper)
		return null;
	}

	@Override
	public int selectDefaultReferenceDataCount(final ReferenceDataSearchCriteria criteria) {
		
		return 0;
	}

	@Override
	public List<ReferenceDataDomain> selectPaginatedReferenceDataBySearchCriteria(final ReferenceDataSearchCriteria criteria) {
		
		return null;
	}

	@Override
	public int selectReferenceDataBySearchCriteriaCount(final ReferenceDataSearchCriteria criteria) {
		
		return 0;
	}
	
	private final class ReferenceDataDomainRowMapper implements RowMapper<ReferenceDataDomain> {

		@Override
		public ReferenceDataDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReferenceDataDomain domain = new ReferenceDataDomain();
			domain.setId(rs.getLong("id"));
			domain.setReferenceDataType(rs.getString("reference_data_type"));
			domain.setReferenceDataLabel(rs.getString("reference_data_label"));
			domain.setReferenceDataValue(rs.getString("reference_data_value"));
			domain.setCreatedBy(rs.getString("created_by"));
			domain.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
			domain.setLastUpdatedBy(rs.getString("last_updated_by"));
			domain.setLastUpdatedTimestamp(rs.getTimestamp("last_updated_timestamp"));
			return domain;
		}	
	}
}