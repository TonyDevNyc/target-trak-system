package com.target.trak.system.web.views.ui.common;

public class NameValuePair {

	private String label;
	private String value;
	
	public NameValuePair() {
		
	}
	
	public NameValuePair(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
