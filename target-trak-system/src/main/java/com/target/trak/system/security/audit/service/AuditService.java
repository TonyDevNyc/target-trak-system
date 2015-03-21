package com.target.trak.system.security.audit.service;

import com.target.trak.system.security.audit.domain.AuditEvent;

public interface AuditService {

	public AuditEvent createAuditEvent(final AuditEvent auditEvent);
}
