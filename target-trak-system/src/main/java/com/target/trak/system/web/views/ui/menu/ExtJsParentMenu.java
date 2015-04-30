package com.target.trak.system.web.views.ui.menu;

public class ExtJsParentMenu extends ExtJsComponent implements Comparable<ExtJsParentMenu> {

	private String text;
	private String iconCls;
	private String iconAlign;
	private int displayOrder;
	private ExtJsChildMenu menu;

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

	public String getIconAlign() {
		return iconAlign;
	}

	public void setIconAlign(String iconAlign) {
		this.iconAlign = iconAlign;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public ExtJsChildMenu getMenu() {
		return menu;
	}

	public void setMenu(ExtJsChildMenu menu) {
		this.menu = menu;
	}

	@Override
	public int compareTo(ExtJsParentMenu parentMenu) {
		return this.displayOrder - parentMenu.getDisplayOrder();
	}
}