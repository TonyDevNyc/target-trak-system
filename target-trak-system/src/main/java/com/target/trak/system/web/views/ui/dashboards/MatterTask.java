package com.target.trak.system.web.views.ui.dashboards;

public class MatterTask {

	private String matterId;
	private String companyName;
	private String primaryContact;
	private String taskType;
	private String createDate;
	private String completionDate;
	private String assignedTo;
	private String completedDate;

	public String getMatterId() {
		return matterId;
	}

	public MatterTask(String matterId, String companyName, String primaryContact, String taskType, String createDate, String completionDate, String assignedTo, String completedDate) {
		this.matterId = matterId;
		this.companyName = companyName;
		this.primaryContact = primaryContact;
		this.taskType = taskType;
		this.createDate = createDate;
		this.completionDate = completionDate;
		this.assignedTo = assignedTo;
		this.completedDate = completedDate;
	}

	public void setMatterId(String matterId) {
		this.matterId = matterId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	public String getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

}
