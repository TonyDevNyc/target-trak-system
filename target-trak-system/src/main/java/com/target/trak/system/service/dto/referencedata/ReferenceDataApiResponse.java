package com.target.trak.system.service.dto.referencedata;

import java.util.List;

import com.target.trak.system.service.dto.common.BaseTargetTrakApiResponse;

public class ReferenceDataApiResponse extends BaseTargetTrakApiResponse {

	private ReferenceDataDto referenceData;
	private List<ReferenceDataDto> referenceDataList;

	public ReferenceDataDto getReferenceData() {
		return referenceData;
	}

	public void setReferenceData(ReferenceDataDto referenceData) {
		this.referenceData = referenceData;
	}

	public List<ReferenceDataDto> getReferenceDataList() {
		return referenceDataList;
	}

	public void setReferenceDataList(List<ReferenceDataDto> referenceDataList) {
		this.referenceDataList = referenceDataList;
	}

}
