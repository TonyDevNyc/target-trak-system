package com.target.trak.system.service.dto.common;

public abstract class AbstractTargetTrakApiRequest implements TargetTrakApiRequest {

	private TargetTrakRequestTypeEnum requestType;

	@Override
	public TargetTrakRequestTypeEnum getRequestType() {
		return requestType;
	}

	@Override
	public void setRequestType(TargetTrakRequestTypeEnum requestType) {
		this.requestType = requestType;
	}

}
