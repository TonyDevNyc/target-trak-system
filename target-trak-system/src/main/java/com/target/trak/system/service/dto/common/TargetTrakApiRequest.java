package com.target.trak.system.service.dto.common;

public interface TargetTrakApiRequest {

	public TargetTrakRequestTypeEnum getRequestType();

	public void setRequestType(final TargetTrakRequestTypeEnum requestType);

	public TargetTrakPagingCriteria getPagingCriteria();

	public void setPagingCriteria(TargetTrakPagingCriteria criteria);
}
