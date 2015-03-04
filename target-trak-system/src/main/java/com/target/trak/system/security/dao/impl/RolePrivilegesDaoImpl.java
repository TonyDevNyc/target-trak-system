package com.target.trak.system.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.target.trak.system.security.dao.RolePrivilegesDao;
import com.target.trak.system.security.domain.TargetTrakPrivilege;

@Repository
public class RolePrivilegesDaoImpl implements RolePrivilegesDao {

private Logger logger = Logger.getLogger(getClass());
	
	private NamedParameterJdbcTemplate rolePrivilegesTemplate;

	@Qualifier("rolePrivilegesQueries")
	@Autowired
	private Properties rolePrivilegesQueries;

	@Autowired
	public RolePrivilegesDaoImpl(@Qualifier("securityDataSource") DataSource dataSource) {
		rolePrivilegesTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<TargetTrakPrivilege> getPrivilegesByRoles(final List<Long> roleIdList) {
		String sql = rolePrivilegesQueries.getProperty("getPrivilegesByRolesSql");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roleIdList", roleIdList);
		return rolePrivilegesTemplate.query(sql, params, new TargetTrakPrivilegeRowMapper());
	}

	@Override

	public List<TargetTrakPrivilege> getPrivilegesByRoleId(final Long roleId) {
		String sql = rolePrivilegesQueries.getProperty("getPrivilegesByRoleIdSql");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("roleId", roleId);
		List<TargetTrakPrivilege> privileges = null;
		try {
			privileges = rolePrivilegesTemplate.query(sql, params, new TargetTrakPrivilegeRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("No privileges found for role: " + roleId, e);
		}
		return privileges;
	}

	private final class TargetTrakPrivilegeRowMapper implements RowMapper<TargetTrakPrivilege> {

		@Override
		public TargetTrakPrivilege mapRow(ResultSet rs, int rowNum) throws SQLException {
			TargetTrakPrivilege privilege = new TargetTrakPrivilege();
			privilege.setId(rs.getLong("privilege_id"));
			privilege.setName(rs.getString("privilege_name"));
			return privilege;
		}
	}
}
