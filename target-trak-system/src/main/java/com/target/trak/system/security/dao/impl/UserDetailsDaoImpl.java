package com.target.trak.system.security.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.domain.TargetTrakUser;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

	private static final Logger logger = Logger.getLogger(UserDetailsDaoImpl.class);

	private NamedParameterJdbcTemplate userDetailTemplate;

	@Qualifier("userDetailQueries")
	@Autowired
	private Properties userDetailQueries;

	@Autowired
	public UserDetailsDaoImpl(@Qualifier("securityDataSource") DataSource dataSource) {
		userDetailTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public TargetTrakUser getUserByUsername(final String username) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sql = userDetailQueries.getProperty("selectUserByUsernameSql");
		params.addValue("username", username);
		TargetTrakUser user = null;

		try {
			user = userDetailTemplate.queryForObject(sql, params, new TargetTrakUserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("No results found for username: " + username, e);
		}
		return user;
	}

	@Override
	public void insertTargetTrakUser(final TargetTrakUser user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", user.getPassword());
		params.addValue("enabled", user.isEnabled());
		params.addValue("firstName", user.getFirstName());
		params.addValue("lastName", user.getLastName());
		params.addValue("email", user.getEmail());
		params.addValue("mobileNumber", user.getMobileNumber());
		params.addValue("registrationDate", user.getRegistrationDate());

		String sql = userDetailQueries.getProperty("insertTargetTrakUserSql");
		userDetailTemplate.update(sql, params);
	}

	@Override
	public TargetTrakUser getUserByEmail(final String email) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", email);
		TargetTrakUser user = null;
		String sql = userDetailQueries.getProperty("selectUserByEmailSql");

		try {
			user = userDetailTemplate.queryForObject(sql, params, new TargetTrakUserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("No results found for email: " + email, e);
		}
		return user;
	}

	private final class TargetTrakUserRowMapper implements RowMapper<TargetTrakUser> {

		@Override
		public TargetTrakUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			TargetTrakUser user = new TargetTrakUser();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEnabled(rs.getBoolean("user_enabled"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmail(rs.getString("email"));
			user.setMobileNumber(rs.getString("mobile_number"));
			user.setRegistrationDate(rs.getTimestamp("registration_date"));
			return user;
		}
	}
}