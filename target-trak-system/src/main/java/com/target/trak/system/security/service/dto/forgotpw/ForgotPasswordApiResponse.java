package com.target.trak.system.security.service.dto.forgotpw;

import com.target.trak.system.service.dto.common.BaseTargetTrakApiResponse;

public class ForgotPasswordApiResponse extends BaseTargetTrakApiResponse {
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
