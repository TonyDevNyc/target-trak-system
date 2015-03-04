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

import com.target.trak.system.security.dao.MenuDao;
import com.target.trak.system.security.domain.TargetTrakMenu;

@Repository
public class MenuDaoImpl implements MenuDao {

	private NamedParameterJdbcTemplate menuTemplate;

	@Qualifier("menuQueries")
	@Autowired
	private Properties menuQueries;
	
	@Autowired
	public MenuDaoImpl(@Qualifier("securityDataSource") DataSource dataSource) {
		menuTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<TargetTrakMenu> selectMenuItemsByPrivileges(final List<Long> privilegeIds) {
		String sql = menuQueries.getProperty("selectMenuByPrivilegesSql");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("privilegeIds", privilegeIds);
		return menuTemplate.query(sql, params, new TargetTrakMenuRowMapper());
	}

	private final class TargetTrakMenuRowMapper implements RowMapper<TargetTrakMenu> {
		
		@Override
		public TargetTrakMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
			TargetTrakMenu menu = new TargetTrakMenu();
			menu.setMenuId(rs.getLong("menu_id"));
			menu.setParentMenuId(rs.getLong("parent_menu_id"));
			menu.setText(rs.getString("text"));
			menu.setDisplayOrder(rs.getInt("display_order"));
			menu.setItemId(rs.getString("item_id"));
			menu.setIconCss(rs.getString("icon_css"));
			menu.setPrivilegeNeeded(rs.getLong("privilege_needed"));
			return menu;
		}
		
	}
}