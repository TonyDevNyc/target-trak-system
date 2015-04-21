package com.target.trak.system.security.audit.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.target.trak.system.security.audit.dao.AuditEventDao;
import com.target.trak.system.security.audit.domain.AuditEvent;

public class AuditEventDaoImpl implements AuditEventDao {

	private final Logger logger = Logger.getLogger(getClass());

	private NamedParameterJdbcTemplate auditEventTemplate;

	private Properties auditEventQueries;

	public AuditEventDaoImpl(DataSource dataSource) {
		auditEventTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public AuditEvent insertAuditEvent(final AuditEvent auditEvent) {
		String sql = auditEventQueries.getProperty("insertAuditEventSql");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", auditEvent.getUsername());
		params.addValue("eventCode", auditEvent.getAuditEventCode());
		params.addValue("eventDescription", auditEvent.getAuditEventDescription());
		params.addValue("timestamp", auditEvent.getTimestamp());
		params.addValue("success", auditEvent.isSuccess());
		params.addValue("errorMessage", auditEvent.getErrorMessage());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = auditEventTemplate.update(sql, params, keyHolder);

		if (count > 0) {
			auditEvent.setId(keyHolder.getKey().longValue());
		} else {
			logger.error("Audit Event was not created");
		}

		return auditEvent;
	}

	public void setAuditEventQueries(Properties auditEventQueries) {
		this.auditEventQueries = auditEventQueries;
	}

	private final class AuditEventRowMapper implements RowMapper<AuditEvent> {

		@Override
		public AuditEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
			AuditEvent event = new AuditEvent();
			event.setId(rs.getLong("id"));
			event.setUsername(rs.getString("username"));
			event.setAuditEventCode(rs.getString("event_code"));
			event.setAuditEventDescription(rs.getString("event_desc"));
			event.setTimestamp(rs.getTimestamp("timestamp"));
			event.setSuccess(rs.getBoolean("success"));
			event.setErrorMessage(rs.getString("error_message"));

			return event;
		}

	}
}
