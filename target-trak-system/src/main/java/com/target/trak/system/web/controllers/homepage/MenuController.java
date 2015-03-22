package com.target.trak.system.web.controllers.homepage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.trak.system.security.context.UserContext;
import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.security.dto.menu.MenuApiRequest;
import com.target.trak.system.security.dto.menu.MenuApiResponse;
import com.target.trak.system.security.service.MenuService;
import com.target.trak.system.web.builders.MenuBuilder;
import com.target.trak.system.web.views.ui.menu.MenuButton;

@Controller
public class MenuController {

	@Autowired
	private UserContext userContext;

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuBuilder menuBuilder;

	@RequestMapping(value = "/buildUserMenu.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MenuButton> buildUserMenu() throws Exception {
		MenuApiRequest request = new MenuApiRequest();
		UserDto currentUser = userContext.getCurrentUser();
		request.setCurrentUser(currentUser);
		MenuApiResponse response = menuService.getMenuItemsForUser(request);
		
		List<MenuButton> allowableMenuButtons = menuBuilder.buildUserMenu(response.getMenuItems());
		return allowableMenuButtons;
	}

}
