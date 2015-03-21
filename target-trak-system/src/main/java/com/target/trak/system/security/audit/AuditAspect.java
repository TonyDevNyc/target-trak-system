package com.target.trak.system.security.audit;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.target.trak.system.security.audit.dao.AuditEventDao;
import com.target.trak.system.security.audit.service.AuditService;

@Aspect
@Component
public class AuditAspect {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private AuditEventDao auditEventDao;

	@Qualifier("auditEventService")
	@Autowired
	private AuditService auditEventService;

	@Around("@annotation(AuditableEvent)")
	public Object audit(ProceedingJoinPoint jp) throws Throwable {
		Throwable exception = null;
		Object retVal = null;
		Method method = AopUtils.getMostSpecificMethod(((MethodSignature) jp.getSignature()).getMethod(), jp.getTarget().getClass());
		logger.debug("inside auditing advice for '" + method.getName() + "'");

		try {
			logger.debug("proceed with original method invocation...");
			retVal = jp.proceed();
			logger.debug("original method invocation completed for method '" + method.getName() + "'");
		} catch (Throwable e) {
			exception = e;
			logger.debug("original method invocation for '" + method.getName() + "' threw exception '" + e.getClass().getName() + "'!");
		}

		AuditableEvent annotation = method.getAnnotation(AuditableEvent.class);
		if (annotation != null) {
			auditEventService.createAuditEvent(annotation, exception);
		}

		if (exception != null) {
			logger.debug("re-throwing '" + exception.getClass().getName() + "' exception");
			throw exception;
		}
		return retVal;
	}
}