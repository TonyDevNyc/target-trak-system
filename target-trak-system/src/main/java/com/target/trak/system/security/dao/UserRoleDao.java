package com.target.trak.system.security.dao;

import java.util.List;

import com.target.trak.system.security.domain.TargetTrakRole;

public interface UserRoleDao {

	public List<TargetTrakRole> getUserRoles(final String username);
}
