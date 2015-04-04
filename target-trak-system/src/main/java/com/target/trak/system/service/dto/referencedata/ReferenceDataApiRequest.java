package com.target.trak.system.service.dto.referencedata;

import com.target.trak.system.service.dto.common.BaseTargetTrakApiRequest;

public class ReferenceDataApiRequest extends BaseTargetTrakApiRequest {

	private ReferenceDataDto referenceDataDto;
	private ReferenceDataSearchCriteriaDto searchCriteria;

	public ReferenceDataDto getReferenceDataDto() {
		return referenceDataDto;
	}

	public void setReferenceDataDto(ReferenceDataDto referenceDataDto) {
		this.referenceDataDto = referenceDataDto;
	}

	public ReferenceDataSearchCriteriaDto getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(ReferenceDataSearchCriteriaDto searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

}
