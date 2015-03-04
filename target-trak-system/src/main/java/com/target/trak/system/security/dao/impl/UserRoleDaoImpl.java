package com.target.trak.system.security.dao.impl;

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
import org.springframework.stereotype.Repository;

import com.target.trak.system.security.dao.UserRoleDao;
import com.target.trak.system.security.domain.TargetTrakRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

	private NamedParameterJdbcTemplate userRoleTemplate;

	@Qualifier("userRoleQueries")
	@Autowired
	private Properties userRoleQueries;

	@Autowired
	public UserRoleDaoImpl(@Qualifier("securityDataSource") DataSource dataSource) {
		userRoleTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<TargetTrakRole> getUserRoles(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		String sql = userRoleQueries.getProperty("selectUserRolesSql");
		return userRoleTemplate.query(sql, params, new TargetTrakRoleRowMapper());
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