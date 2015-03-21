package com.target.trak.system.security.audit.dao;

import com.target.trak.system.security.audit.domain.AuditEvent;

public interface AuditEventDao {

	public AuditEvent insertAuditEvent(final AuditEvent auditEvent);
}
