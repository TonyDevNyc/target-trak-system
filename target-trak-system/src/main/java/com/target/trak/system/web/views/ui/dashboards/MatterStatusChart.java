package com.target.trak.system.web.views.ui.dashboards;

public class MatterStatusChart {

	private String status;
	private String label;
	private double percentage;
	private int count;

	public MatterStatusChart() {
	}

	public MatterStatusChart(String status, String label, double percentage, int count) {
		this.status = status;
		this.label = label;
		this.percentage = percentage;
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}