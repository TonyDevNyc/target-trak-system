package com.target.trak.system.security.context;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.target.trak.system.security.domain.TargetTrakRole;
import com.target.trak.system.security.domain.TargetTrakUser;
import com.target.trak.system.security.dto.UserDto;

@Component("userContext")
public class UserContextImpl implements UserContext {

	@Autowired
	private ConversionService conversionService;
	
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TargetTrakRole> getUserRoles() {
		//TODO determine if this is safe
		return (List<TargetTrakRole>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}

	@Override
	public UserDto getCurrentUser() {
		TargetTrakUser targetTrakUser = (TargetTrakUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return conversionService.convert(targetTrakUser, UserDto.class);
	}

}
