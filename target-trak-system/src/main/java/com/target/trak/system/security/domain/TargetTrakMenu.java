package com.target.trak.system.security.domain;

public class TargetTrakMenu {

	private Long menuId;
	private Long parentMenuId;
	private String text;
	private int displayOrder;
	private String itemId;
	private String iconCss;
	private Long privilegeNeeded;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getIconCss() {
		return iconCss;
	}

	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}

	public Long getPrivilegeNeeded() {
		return privilegeNeeded;
	}

	public void setPrivilegeNeeded(Long privilegeNeeded) {
		this.privilegeNeeded = privilegeNeeded;
	}

}
