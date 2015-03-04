package com.target.trak.system.web.views.ui.menu;

import java.util.List;

public class Menu {

	private String xtype;
	private List<MenuItem> items;
	
	public Menu(){}
	
	public Menu(String xtype) {
		this.xtype = xtype;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

}
