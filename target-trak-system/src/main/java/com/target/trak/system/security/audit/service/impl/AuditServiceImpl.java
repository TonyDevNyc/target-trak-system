package com.target.trak.system.security.audit.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.target.trak.system.security.audit.AuditableEvent;
import com.target.trak.system.security.audit.dao.AuditEventDao;
import com.target.trak.system.security.audit.domain.AuditEvent;
import com.target.trak.system.security.audit.service.AuditService;
import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.service.dto.common.TargetTrakApiResponse;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AuditServiceImpl implements AuditService {

	private AuditEventDao auditEventDao;

	private UserContext userContext;

	@Override
	public void createAuditEvent(final AuditableEvent auditableEvent, final TargetTrakApiResponse response) {
		AuditEvent auditEvent = buildAuditEvent(auditableEvent, response);
		auditEventDao.insertAuditEvent(auditEvent);
	}

	private AuditEvent buildAuditEvent(final AuditableEvent auditableEvent, final TargetTrakApiResponse response) {
		AuditEvent auditEvent = new AuditEvent();
		auditEvent.setAuditEventCode(auditableEvent.auditableEventCode().toString());
		auditEvent.setAuditEventDescription(auditableEvent.auditableEventCode().description());
		auditEvent.setUsername(getCurrentUser());
		Calendar currentTime = Calendar.getInstance();
		auditEvent.setTimestamp(new Timestamp(currentTime.getTimeInMillis()));

		auditEvent.setSuccess(response.isSuccess());
		if (response.isSuccess()) {
			auditEvent.setErrorMessage(null);
		} else {
			auditEvent.setErrorMessage(response.getMessage());
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

	public void setAuditEventDao(AuditEventDao auditEventDao) {
		this.auditEventDao = auditEventDao;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
}