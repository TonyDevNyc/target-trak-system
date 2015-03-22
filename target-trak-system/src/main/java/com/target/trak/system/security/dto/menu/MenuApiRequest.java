package com.target.trak.system.security.dto.menu;

import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.service.dto.TargetTrakApiRequest;

public class MenuApiRequest extends TargetTrakApiRequest {

	private UserDto currentUser;

	public UserDto getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDto currentUser) {
		this.currentUser = currentUser;
	}
	
	
}
