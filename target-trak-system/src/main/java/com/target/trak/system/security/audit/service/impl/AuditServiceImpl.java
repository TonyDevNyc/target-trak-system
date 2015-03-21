package com.target.trak.system.security.audit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.audit.dao.AuditEventDao;
import com.target.trak.system.security.audit.domain.AuditEvent;
import com.target.trak.system.security.audit.service.AuditService;

@Transactional(propagation=Propagation.REQUIRES_NEW)
@Service("auditEventService")
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditEventDao auditEventDao;
	
	@Override
	public AuditEvent createAuditEvent(final AuditEvent auditEvent) {
		return auditEventDao.insertAuditEvent(auditEvent);
	}

}
