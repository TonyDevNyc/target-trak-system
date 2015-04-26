package com.target.trak.system.web.controllers.homepage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.security.dto.menu.MenuApiRequest;
import com.target.trak.system.security.dto.menu.MenuApiResponse;
import com.target.trak.system.security.service.MenuService;
import com.target.trak.system.web.views.builders.MenuBuilder;
import com.target.trak.system.web.views.ui.menu.ExtJsParentMenu;

@Controller
public class MenuController {

	private UserContext userContext;

	private MenuService menuService;

	private MenuBuilder menuBuilder;

	@RequestMapping(value = "/buildUserMenu.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody 
	List<ExtJsParentMenu> buildUserMenu() throws Exception {
		MenuApiRequest request = new MenuApiRequest();
		UserDto currentUser = userContext.getCurrentUser();
		request.setCurrentUser(currentUser);
		MenuApiResponse response = menuService.getMenuItemsForUser(request);
		return menuBuilder.buildUserInterfaceMenu(response.getMenuItems());
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void setMenuBuilder(MenuBuilder menuBuilder) {
		this.menuBuilder = menuBuilder;
	}
}