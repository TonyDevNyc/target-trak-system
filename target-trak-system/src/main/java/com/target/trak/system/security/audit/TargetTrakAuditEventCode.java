package com.target.trak.system.security.audit;

public enum TargetTrakAuditEventCode {

	CREATE_REFERENCE_DATA("Created Reference Data Item"),

	SEARCH_REFERENCE_DATA_BY_CRITERIA("Searched Reference Data Items by Criteria"),

	GET_REFERENCE_DATA_TYPES("Get Reference Data Types"),
	
	GET_REFERENCE_DATA_BY_TYPE("Get Reference Data by Type"),
	
	UPDATE_REFERENCE_DATA("Updated Reference Data Item"),

	DELETE_REFERENCE_DATA("Deleted Reference Data Item"),

	USER_LOGIN("User attempted to authenticate into system"),

	USER_LOGOUT("User logged out of system"),

	BUILD_USER_MENU("User built application menu"),

	REGISTER_NEW_USER("New user registered into system"),

	HANDLE_FORGOT_PASSWORD("User accessed handle forgot password"),
	
	CREATE_COMPANY("Created Company"),
	
	SEARCH_COMPANY_BY_CRITERIA("Search Company by Criteria"),
	
	GET_COMPANY_BY_ID("Retrieve Company by Id"),
	
	UPDATE_COMPANY("Company Updated");

	private final String description;

	TargetTrakAuditEventCode(String description) {
		this.description = description;
	}

	public String description() {
		return this.description;
	}
}
