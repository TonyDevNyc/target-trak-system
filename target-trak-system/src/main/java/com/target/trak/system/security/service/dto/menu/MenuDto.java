package com.target.trak.system.security.service.dto.menu;

public class MenuDto implements Comparable<MenuDto> {

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

	@Override
	public int compareTo(MenuDto compareMenu) {
		return this.displayOrder - compareMenu.getDisplayOrder();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + displayOrder;
		result = prime * result + ((iconCss == null) ? 0 : iconCss.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((parentMenuId == null) ? 0 : parentMenuId.hashCode());
		result = prime * result + ((privilegeNeeded == null) ? 0 : privilegeNeeded.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		MenuDto other = (MenuDto) obj;
		if (displayOrder != other.displayOrder)
			return false;
		if (iconCss == null) {
			if (other.iconCss != null)
				return false;
		} else if (!iconCss.equals(other.iconCss))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (parentMenuId == null) {
			if (other.parentMenuId != null)
				return false;
		} else if (!parentMenuId.equals(other.parentMenuId))
			return false;
		if (privilegeNeeded == null) {
			if (other.privilegeNeeded != null)
				return false;
		} else if (!privilegeNeeded.equals(other.privilegeNeeded))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
}