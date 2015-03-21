package com.target.trak.system.security.audit;

public enum AuditEventCode {

	CREATE_REFERENCE_DATA("Created Reference Data Item"),
	
	READ_REFERENCE_DATA("Read Reference Data Items"),
	
	UPDATE_REFERENCE_DATA("Updated Reference Data Item"),
	
	DELETE_REFERENCE_DATA("Deleted Reference Data Item"),
	
	USER_LOGIN("User attempted to authenticate into system"),
	
	USER_LOGOUT("User logged out of system"),
	
	BUILD_USER_MENU("User built application menu"),
	
	REGISTER_NEW_USER("New user registered into system"),
	
	HANDLE_FORGOT_PASSWORD("User accessed handle forgot password");
	
	private final String description;
	
	AuditEventCode(String description) {
		this.description = description;
	}
	
	public String description() {
		return this.description;
	}
}
