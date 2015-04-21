package com.target.trak.system.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.target.trak.system.security.dao.UserRoleDao;
import com.target.trak.system.security.domain.TargetTrakRole;

public class UserRoleDaoImpl implements UserRoleDao {

	private NamedParameterJdbcTemplate userRoleTemplate;

	private Properties userRoleQueries;

	public UserRoleDaoImpl(DataSource dataSource) {
		userRoleTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<TargetTrakRole> getUserRoles(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		String sql = userRoleQueries.getProperty("selectUserRolesSql");
		return userRoleTemplate.query(sql, params, new TargetTrakRoleRowMapper());
	}

	public void setUserRoleQueries(Properties userRoleQueries) {
		this.userRoleQueries = userRoleQueries;
	}

	private final class TargetTrakRoleRowMapper implements RowMapper<TargetTrakRole> {

		@Override
		public TargetTrakRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			TargetTrakRole role = new TargetTrakRole();
			role.setRoleId(rs.getLong("role_id"));
			role.setName(rs.getString("role_name"));
			return role;
		}
	}
}