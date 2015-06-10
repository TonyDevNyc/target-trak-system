package com.target.trak.system.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.target.trak.system.security.dao.RolePrivilegesDao;
import com.target.trak.system.security.dao.UserDetailsDao;
import com.target.trak.system.security.dao.UserRoleDao;
import com.target.trak.system.security.domain.TargetTrakRole;
import com.target.trak.system.security.domain.TargetTrakUser;

public class TargetTrakUserDetailsServiceImpl implements UserDetailsService {

	private Logger logger = Logger.getLogger(getClass());
	
	private UserDetailsDao userDetailsDao;

	private UserRoleDao userRoleDao;

	private RolePrivilegesDao rolePrivilegesDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(username)) {
			logger.error("Username is empty");
			throw new UsernameNotFoundException("Username is empty");
		}

		TargetTrakUser user = (TargetTrakUser) userDetailsDao.getUserByUsername(username.toLowerCase());
		if (user == null) {
			logger.error("Username: [" + username + "] not found");
			throw new UsernameNotFoundException("Username: [" + username + "] not found");
		}

		List<TargetTrakRole> roles = userRoleDao.getUserRoles(username.toLowerCase());
		user.setAuthorities(roles);

		for (TargetTrakRole role : roles) {
			role.setPrivileges(rolePrivilegesDao.getPrivilegesByRoleId(role.getRoleId()));
		}
		return user;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public void setRolePrivilegesDao(RolePrivilegesDao rolePrivilegesDao) {
		this.rolePrivilegesDao = rolePrivilegesDao;
	}
}