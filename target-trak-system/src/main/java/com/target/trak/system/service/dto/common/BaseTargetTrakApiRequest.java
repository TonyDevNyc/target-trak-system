package com.target.trak.system.service.dto.common;

public abstract class BaseTargetTrakApiRequest implements TargetTrakApiRequest {

	private TargetTrakRequestTypeEnum requestType;
	private TargetTrakPagingCriteria pagingCriteria;

	@Override
	public TargetTrakPagingCriteria getPagingCriteria() {
		return pagingCriteria;
	}

	@Override
	public void setPagingCriteria(TargetTrakPagingCriteria criteria) {
		this.pagingCriteria = criteria;
	}

	@Override
	public TargetTrakRequestTypeEnum getRequestType() {
		return requestType;
	}

	@Override
	public void setRequestType(TargetTrakRequestTypeEnum requestType) {
		this.requestType = requestType;
	}

}
