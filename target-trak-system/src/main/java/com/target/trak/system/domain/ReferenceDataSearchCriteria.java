package com.target.trak.system.domain;

public class ReferenceDataSearchCriteria {

	private String referenceDataType;
	private int start;
	private int end;
	private int page;
	private String sortField;
	private String sortDirection;

	public String getReferenceDataType() {
		return referenceDataType;
	}

	public void setReferenceDataType(String referenceDataType) {
		this.referenceDataType = referenceDataType;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
}