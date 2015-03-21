package com.target.trak.system.security.audit.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.audit.AuditableEvent;
import com.target.trak.system.security.audit.dao.AuditEventDao;
import com.target.trak.system.security.audit.domain.AuditEvent;
import com.target.trak.system.security.audit.service.AuditService;
import com.target.trak.system.security.context.UserContext;

@Transactional(propagation=Propagation.REQUIRES_NEW)
@Service("auditEventService")
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditEventDao auditEventDao;
	
	@Autowired
	private UserContext userContext;
	
	@Override
	public void createAuditEvent(final AuditableEvent auditableEvent, final Throwable exception) {
		AuditEvent auditEvent = buildAuditEvent(auditableEvent, exception);
		auditEventDao.insertAuditEvent(auditEvent);
	}
	
	private AuditEvent buildAuditEvent(final AuditableEvent auditableEvent, final Throwable exception) {
		AuditEvent auditEvent = new AuditEvent();
		auditEvent.setAuditEventCode(auditableEvent.auditableEventCode().toString());
		auditEvent.setAuditEventDescription(auditableEvent.auditableEventCode().description());
		auditEvent.setUsername(getCurrentUser());
		Calendar currentTime = Calendar.getInstance();
		auditEvent.setTimestamp(new Timestamp(currentTime.getTimeInMillis()));

		boolean isSuccessful = exception == null;
		auditEvent.setSuccess(isSuccessful);
		if (!isSuccessful) {
			auditEvent.setErrorMessage(exception.getMessage());
		}
		return auditEvent;
	}
	
	private String getCurrentUser() {
		if (userContext == null || userContext.getCurrentUser() == null) {
			return "Anonymous User";
		} else {
			return userContext.getCurrentUser().getUsername();
		}
	}



}
