package com.target.trak.system.security.dto.menu;

import java.util.List;

import com.target.trak.system.service.dto.TargetTrakApiResponse;

public class MenuApiResponse extends TargetTrakApiResponse {

	private List<MenuDto> menuItems;

	public List<MenuDto> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuDto> menuItems) {
		this.menuItems = menuItems;
	}
	
	
}
