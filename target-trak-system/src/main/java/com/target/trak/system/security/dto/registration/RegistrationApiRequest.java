package com.target.trak.system.security.dto.registration;

import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.service.dto.common.BaseTargetTrakApiRequest;

public class RegistrationApiRequest extends BaseTargetTrakApiRequest {

	private UserDto userRegistration;

	public UserDto getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserDto userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	

}
