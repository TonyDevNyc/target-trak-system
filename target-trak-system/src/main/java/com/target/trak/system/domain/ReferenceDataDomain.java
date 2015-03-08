package com.target.trak.system.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class ReferenceDataDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String referenceDataType;
	private String referenceDataLabel;
	private String referenceDataValue;
	private String createdBy;
	private Timestamp createdTimestamp;
	private String lastUpdatedBy;
	private Timestamp lastUpdatedTimestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getReferenceDataValue() {
		return referenceDataValue;
	}

	public void setReferenceDataValue(String referenceDataValue) {
		this.referenceDataValue = referenceDataValue;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
}