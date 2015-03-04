package com.target.trak.system.security.dao;

import com.target.trak.system.security.domain.TargetTrakUser;

public interface UserDetailsDao {

	public TargetTrakUser getUserByUsername(final String username);
	
	public void insertTargetTrakUser(final TargetTrakUser user);
	
	public TargetTrakUser getUserByEmail(final String email);
}
