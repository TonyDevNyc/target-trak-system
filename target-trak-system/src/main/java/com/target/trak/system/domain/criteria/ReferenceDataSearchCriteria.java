package com.target.trak.system.domain.criteria;


public class ReferenceDataSearchCriteria extends BaseSearchCriteria {

	private String referenceDataType;
	private String referenceDataLabel;

	public String getReferenceDataType() {
		return referenceDataType;
	}

	public void setReferenceDataType(String referenceDataType) {
		this.referenceDataType = referenceDataType;
	}

	public String getReferenceDataLabel() {
		return referenceDataLabel;
	}

	public void setReferenceDataLabel(String referenceDataLabel) {
		this.referenceDataLabel = referenceDataLabel;
	}
}