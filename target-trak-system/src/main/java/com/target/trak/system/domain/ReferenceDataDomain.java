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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdTimestamp == null) ? 0 : createdTimestamp.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result + ((lastUpdatedTimestamp == null) ? 0 : lastUpdatedTimestamp.hashCode());
		result = prime * result + ((referenceDataLabel == null) ? 0 : referenceDataLabel.hashCode());
		result = prime * result + ((referenceDataType == null) ? 0 : referenceDataType.hashCode());
		result = prime * result + ((referenceDataValue == null) ? 0 : referenceDataValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReferenceDataDomain other = (ReferenceDataDomain) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTimestamp == null) {
			if (other.createdTimestamp != null)
				return false;
		} else if (!createdTimestamp.equals(other.createdTimestamp))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		if (lastUpdatedTimestamp == null) {
			if (other.lastUpdatedTimestamp != null)
				return false;
		} else if (!lastUpdatedTimestamp.equals(other.lastUpdatedTimestamp))
			return false;
		if (referenceDataLabel == null) {
			if (other.referenceDataLabel != null)
				return false;
		} else if (!referenceDataLabel.equals(other.referenceDataLabel))
			return false;
		if (referenceDataType == null) {
			if (other.referenceDataType != null)
				return false;
		} else if (!referenceDataType.equals(other.referenceDataType))
			return false;
		if (referenceDataValue == null) {
			if (other.referenceDataValue != null)
				return false;
		} else if (!referenceDataValue.equals(other.referenceDataValue))
			return false;
		return true;
	}
}