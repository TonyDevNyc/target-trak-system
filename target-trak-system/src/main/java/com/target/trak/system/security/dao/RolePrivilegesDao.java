package com.target.trak.system.security.dao;

import java.util.List;

import com.target.trak.system.security.domain.TargetTrakPrivilege;

public interface RolePrivilegesDao {

	public List<TargetTrakPrivilege> getPrivilegesByRoles(final List<Long> roleIdList);
	
	public List<TargetTrakPrivilege> getPrivilegesByRoleId(final Long roleId);
}
