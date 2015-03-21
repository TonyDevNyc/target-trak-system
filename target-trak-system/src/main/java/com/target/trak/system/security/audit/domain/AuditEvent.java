package com.target.trak.system.security.audit.domain;

import java.sql.Timestamp;

public class AuditEvent {

	private Long id;
	private String username;
	private String auditEventCode;
	private String auditEventDescription;
	private Timestamp timestamp;
	private boolean success;
	private String errorMessage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuditEventCode() {
		return auditEventCode;
	}

	public void setAuditEventCode(String auditEventCode) {
		this.auditEventCode = auditEventCode;
	}

	public String getAuditEventDescription() {
		return auditEventDescription;
	}

	public void setAuditEventDescription(String auditEventDescription) {
		this.auditEventDescription = auditEventDescription;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
