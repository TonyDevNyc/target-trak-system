package com.target.trak.system.security.domain;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class TargetTrakRole implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	private String name;
	private List<TargetTrakPrivilege> privileges;

	@Override
	public String getAuthority() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TargetTrakPrivilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<TargetTrakPrivilege> privileges) {
		this.privileges = privileges;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
