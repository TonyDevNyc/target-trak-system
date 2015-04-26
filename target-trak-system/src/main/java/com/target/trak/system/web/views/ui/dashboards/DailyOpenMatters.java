package com.target.trak.system.web.views.ui.dashboards;

public class DailyOpenMatters {

	private String date;
	private int matterOpenCount;

	public DailyOpenMatters() {
	}

	public DailyOpenMatters(String date, int matterOpenCount) {
		this.date = date;
		this.matterOpenCount = matterOpenCount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMatterOpenCount() {
		return matterOpenCount;
	}

	public void setMatterOpenCount(int matterOpenCount) {
		this.matterOpenCount = matterOpenCount;
	}
}