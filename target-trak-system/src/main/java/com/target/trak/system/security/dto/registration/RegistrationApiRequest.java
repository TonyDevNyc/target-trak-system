package com.target.trak.system.security.dto.registration;

import com.target.trak.system.security.dto.UserDto;

public class RegistrationApiRequest {

	private UserDto userRegistration;

	public UserDto getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(UserDto userRegistration) {
		this.userRegistration = userRegistration;
	}
	
	

}
