package com.target.trak.system.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.target.trak.system.dao.ReferenceDataDao;
import com.target.trak.system.domain.ReferenceDataDomain;

@Repository
public class ReferenceDataDaoImpl implements ReferenceDataDao {

	private NamedParameterJdbcTemplate refDataTemplate;
	
	@Autowired
	public ReferenceDataDaoImpl(@Qualifier("dwDataSource") DataSource dataSource) {
		refDataTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public ReferenceDataDomain insertReferenceData(ReferenceDataDomain referenceData) {
		
		return null;
	}

	@Override
	public List<ReferenceDataDomain> getAllPaginatedReferenceData() {
		
		return null;
	}

	@Override
	public int getAllReferenceDataCount() {
		
		return 0;
	}

}
