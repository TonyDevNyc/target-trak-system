package com.target.trak.system.web.views.ui.menu;

public class ExtJsMenuItem implements Comparable<ExtJsMenuItem> {

	private int displayOrder;
	private String text;
	private String iconCls;
	private String itemId;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Override
	public int compareTo(ExtJsMenuItem menuItem) {
		return this.displayOrder - menuItem.getDisplayOrder();
	}
}