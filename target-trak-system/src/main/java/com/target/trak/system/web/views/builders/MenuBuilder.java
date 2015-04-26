package com.target.trak.system.web.views.builders;

import java.util.List;

import com.target.trak.system.security.dto.menu.MenuDto;
import com.target.trak.system.web.views.ui.menu.ExtJsComponent;
import com.target.trak.system.web.views.ui.menu.ExtJsParentMenu;

public interface MenuBuilder {

	public List<ExtJsParentMenu> buildUserInterfaceMenu(final List<MenuDto> permissableMenuItems);
}
