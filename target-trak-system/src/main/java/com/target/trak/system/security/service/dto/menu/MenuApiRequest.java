package com.target.trak.system.security.service.dto.menu;

import com.target.trak.system.security.service.dto.UserDto;
import com.target.trak.system.service.dto.common.BaseTargetTrakApiRequest;

public class MenuApiRequest extends BaseTargetTrakApiRequest {
	private UserDto currentUser;

	public UserDto getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDto currentUser) {
		this.currentUser = currentUser;
	}
}
