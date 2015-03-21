package com.target.trak.system.security.audit.service;

import com.target.trak.system.security.audit.AuditableEvent;

public interface AuditService {

	public void createAuditEvent(final AuditableEvent auditableEvent, Throwable exception);

}
