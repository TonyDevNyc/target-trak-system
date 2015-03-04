package com.target.trak.system.web.builders;

import java.util.List;

import com.target.trak.system.security.dto.menu.MenuDto;
import com.target.trak.system.web.views.ui.menu.MenuButton;

public interface MenuBuilder {

	public List<MenuButton> buildUserMenu(final List<MenuDto> permissableMenuItems);
	
	public List<MenuButton> buildMockUserMenu(final List<MenuDto> permissableMenuItems);
}
