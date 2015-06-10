package com.target.trak.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.target.trak.system.dao.ContactDao;
import com.target.trak.system.domain.ContactDomain;
import com.target.trak.system.domain.criteria.ContactSearchCriteria;

public class ContactDaoImpl implements ContactDao {

	private final Logger logger = Logger.getLogger(getClass());

	private Properties contactQueries;

	private NamedParameterJdbcTemplate contactTemplate;

	public ContactDaoImpl(DataSource dataSource) {
		contactTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public ContactDomain insertContact(final ContactDomain contact) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("contactType", contact.getContactType());
		params.addValue("title", contact.getTitle());
		params.addValue("firstName", contact.getFirstName());
		params.addValue("lastName", contact.getLastName());
		params.addValue("middleInitial", contact.getMiddleInitial());
		params.addValue("suffix", contact.getSuffix());
		params.addValue("telephoneNumber", contact.getTelephoneNumber());
		params.addValue("emailAddress", contact.getEmailAddress());
		params.addValue("companyId", contact.getCompany().getId());
		params.addValue("activeAtCompany", contact.isActiveAtCompany());
		params.addValue("createdBy", contact.getCreatedBy());
		params.addValue("createdTs", contact.getCreatedTimestamp());
		params.addValue("lastUpdatedBy", contact.getLastUpdatedBy());
		params.addValue("lastUpdatedTs", contact.getLastUpdatedTimestamp());

		String sql = contactQueries.getProperty("insertContactSql");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = contactTemplate.update(sql, params, keyHolder);

		if (count > 0) {
			contact.setId(keyHolder.getKey().longValue());
		} else {
			logger.error("Contact was not inserted");
			throw new RuntimeException("Contact was not inserted");
		}
		return contact;
	}

	@Override
	public ContactDomain selectContactById(final Long id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		String sql = contactQueries.getProperty("selectContactByIdSql");
		return contactTemplate.queryForObject(sql, params, new ContactRowMapper());
	}

	@Override
	public List<ContactDomain> selectContactsByCriteria(ContactSearchCriteria criteria) {

		return null;
	}

	@Override
	public ContactDomain updateContact(ContactDomain contact) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("contactType", contact.getContactType());
		params.addValue("title", contact.getTitle());
		params.addValue("lastName", contact.getLastName());
		params.addValue("telephoneNumber", contact.getTelephoneNumber());
		params.addValue("emailAddress", contact.getEmailAddress());
		params.addValue("activeAtCompany", contact.isActiveAtCompany());
		params.addValue("lastUpdatedBy", contact.getLastUpdatedBy());
		params.addValue("lastUpdatedTs", contact.getLastUpdatedTimestamp());
		params.addValue("id", contact.getId());

		String sql = contactQueries.getProperty("updateContactSql");
		int count = contactTemplate.update(sql, params);
		if (count != 1) {
			logger.error("Contact was not updated");
			throw new RuntimeException("Contact was not updated!");
		}
		logger.info("Contact was updated successfully");
		return contact;
	}

	@Override
	public int selectContactsByCriteriaCount(ContactSearchCriteria criteria) {
		
		return 0;
	}

	private final class ContactRowMapper implements RowMapper<ContactDomain> {

		@Override
		public ContactDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
			ContactDomain domain = new ContactDomain();
			domain.setId(rs.getLong("id"));
			domain.setContactType(rs.getString("contact_type"));
			domain.setTitle(rs.getString("title"));
			domain.setFirstName(rs.getString("first_name"));
			domain.setLastName(rs.getString("last_name"));
			domain.setMiddleInitial(rs.getString("middle_initial"));
			domain.setSuffix(rs.getString("suffix"));
			domain.setTelephoneNumber(rs.getString("telephone_number"));
			domain.setEmailAddress(rs.getString("email_address"));
			domain.setActiveAtCompany(rs.getBoolean("active_at_company"));
			domain.setCreatedBy(rs.getString("created_by"));
			domain.setCreatedTimestamp(rs.getTimestamp("created_ts"));
			domain.setLastUpdatedBy(rs.getString("last_updated_by"));
			domain.setLastUpdatedTimestamp(rs.getTimestamp("last_updated_ts"));
			return domain;
		}
	}

	public void setContactQueries(Properties contactQueries) {
		this.contactQueries = contactQueries;
	}

	public void setContactTemplate(NamedParameterJdbcTemplate contactTemplate) {
		this.contactTemplate = contactTemplate;
	}
}