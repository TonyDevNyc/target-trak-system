package com.target.trak.system.security.audit.service;

import com.target.trak.system.security.audit.AuditableEvent;
import com.target.trak.system.service.dto.TargetTrakApiResponse;

public interface AuditService {

	public void createAuditEvent(final AuditableEvent auditableEvent, final TargetTrakApiResponse response);

}
