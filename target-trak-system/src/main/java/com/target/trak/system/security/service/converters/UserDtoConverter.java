package com.target.trak.system.security.service.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.security.domain.TargetTrakPrivilege;
import com.target.trak.system.security.domain.TargetTrakRole;
import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.security.service.dto.PrivilegeDto;
import com.target.trak.system.security.service.dto.RoleDto;
import com.target.trak.system.security.service.dto.UserDto;

public class UserDtoConverter implements Converter<TargetTrakUser, UserDto> {

	@SuppressWarnings("unchecked")
	@Override
	public UserDto convert(TargetTrakUser domain) {
		UserDto dto = new UserDto();
		dto.setUsername(domain.getUsername());
		dto.setEmail(domain.getEmail());
		dto.setFirstName(domain.getFirstName());
		dto.setLastName(domain.getLastName());
		dto.setRoles(buildRoles((List<TargetTrakRole>) domain.getAuthorities()));
		return dto;
	}

	private List<RoleDto> buildRoles(final List<TargetTrakRole> targetTrakRoles) {
		List<RoleDto> roles = new ArrayList<RoleDto>();
		RoleDto role = null;
		for (TargetTrakRole targetTrakRole : targetTrakRoles) {
			role = new RoleDto();
			role.setRoleId(targetTrakRole.getRoleId());
			role.setRoleName(targetTrakRole.getName());
			role.setPrivileges(buildPrivilegeList(targetTrakRole.getPrivileges()));
			roles.add(role);
		}
		return roles;
	}

	private List<PrivilegeDto> buildPrivilegeList(final List<TargetTrakPrivilege> targetTrakPrivileges) {
		List<PrivilegeDto> privileges = new ArrayList<PrivilegeDto>();
		PrivilegeDto privilege = null;
		for (TargetTrakPrivilege targetTrakPrivilege : targetTrakPrivileges) {
			privilege = new PrivilegeDto();
			privilege.setPrivilegeId(targetTrakPrivilege.getId());
			privilege.setPrivilegeName(targetTrakPrivilege.getName());
			privileges.add(privilege);
		}
		return privileges;
	}
}