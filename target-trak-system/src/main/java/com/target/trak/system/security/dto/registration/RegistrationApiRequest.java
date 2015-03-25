package com.target.trak.system.security.dto.registration;

import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.service.dto.TargetTrakApiRequest;

public class RegistrationApiRequest extends TargetTrakApiRequest {

	private UserDto userRegistration;

	public UserDto getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserDto userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	

}
