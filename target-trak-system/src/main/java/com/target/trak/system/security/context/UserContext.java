package com.target.trak.system.security.context;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.target.trak.system.security.domain.TargetTrakRole;
import com.target.trak.system.security.dto.UserDto;

public interface UserContext {

	public Authentication getAuthentication();
	
	public List<TargetTrakRole> getUserRoles();
	
	public UserDto getCurrentUser();
}
