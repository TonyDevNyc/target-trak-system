package com.target.trak.system.web.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.target.trak.system.security.dto.menu.MenuDto;
import com.target.trak.system.web.views.ui.menu.Menu;
import com.target.trak.system.web.views.ui.menu.MenuButton;
import com.target.trak.system.web.views.ui.menu.MenuItem;

@Component("menuBuilder")
public class MenuBuilderImpl implements MenuBuilder {
	
	private static final String MENU_XTYPE = "menu";
	private static final String MENU_BUTTON_XTYPE = "button";
	private static final String ICON_POSITION = "left";

	@Override
	public List<MenuButton> buildUserMenu(List<MenuDto> permissibleMenuItems) {
		Map<MenuDto, List<MenuDto>> map = buildMapOfChildrenMenuItems(permissibleMenuItems);	
		List<MenuButton> menuButtons = new ArrayList<MenuButton>();
		
		MenuButton parentMenuButton = null;
		Menu childMenu = null;
		List<MenuItem> childMenuItems = null;
		for (Map.Entry<MenuDto, List<MenuDto>> entry : map.entrySet()) {
			MenuDto parentDto = entry.getKey();
			parentMenuButton = buildMenuButton(MENU_BUTTON_XTYPE, parentDto.getText(), parentDto.getIconCss(), ICON_POSITION);
			
			childMenu = new Menu(MENU_XTYPE);
			List<MenuDto> childrenDtos = entry.getValue();
			childMenuItems = createMenuItems(childrenDtos);
			childMenu.setItems(childMenuItems);
			
			parentMenuButton.setMenu(childMenu);
			menuButtons.add(parentMenuButton);
		}
		return menuButtons;
	}
	
	private List<MenuItem> createMenuItems(final List<MenuDto> childMenuList) {
		List<MenuItem> childMenuItems = new ArrayList<MenuItem>();
		MenuItem menuItem = null;
		for (MenuDto childMenu : childMenuList) {
			menuItem = new MenuItem();
			menuItem.setText(childMenu.getText());
			menuItem.setItemId(childMenu.getItemId());
			menuItem.setIconCls(childMenu.getIconCss());
			childMenuItems.add(menuItem);
		}
		return childMenuItems;
	}
	
	private MenuButton buildMenuButton(final String xtype, final String text, final String iconCls, final String iconAlign) {
		MenuButton menuButton = new MenuButton();
		menuButton.setXtype(xtype);
		menuButton.setText(text);
		menuButton.setIconCls(iconCls);
		menuButton.setIconAlign(iconAlign);
		return menuButton;
	}
	
	private Map<MenuDto, List<MenuDto>> buildMapOfChildrenMenuItems(final List<MenuDto> permissibleMenuItems) {
		Map<MenuDto, List<MenuDto>> map = new HashMap<MenuDto, List<MenuDto>>();
		List<MenuDto> parents = getParentMenuItems(permissibleMenuItems);
		
		for (MenuDto parent : parents) {
			map.put(parent, findChildrenMenuDto(parent.getMenuId(), permissibleMenuItems));
		}
		return map;
	}
	
	private List<MenuDto> findChildrenMenuDto(final Long parentId, final List<MenuDto> permissibleMenuItems) {
		List<MenuDto> childMenuItems = new ArrayList<MenuDto>();
		for (MenuDto menu : permissibleMenuItems) {
			if (parentId == menu.getParentMenuId()) {
				childMenuItems.add(menu);
			}
		}
		Collections.sort(childMenuItems);
		return childMenuItems;
	}

	
	
	
	private List<MenuDto> getParentMenuItems(final List<MenuDto> permissibleMenuItems) {
		List<MenuDto> parents = new ArrayList<MenuDto>();
		if (permissibleMenuItems == null || permissibleMenuItems.isEmpty()) {
			return parents;
		}
		
		for (MenuDto permissibleMenuItem : permissibleMenuItems) {
			if (permissibleMenuItem.getParentMenuId() == 0) {
				parents.add(permissibleMenuItem);
			}
		}
		Collections.sort(parents);
		return parents;
	}
	
	/**
	 * xtype : 'button', text : 'Home', iconCls : 'edit-icon', iconAlign : 'left',
	 * 
	 * @return
	 */

	private MenuButton createMenuButton(final String xtype, final String text, final String iconCls, final String iconAlign) {
		MenuButton button = new MenuButton();
		button.setXtype(xtype);
		button.setText(text);
		button.setIconCls(iconCls);
		button.setIconAlign(iconAlign);

		return button;
	}

	/**
	 * xtype: 'menu', items: [ { text: 'Homepage', iconCls: 'edit-icon' }, { text: 'UserProfile', iconCls: 'edit-icon' } ]
	 * 
	 * @return
	 */
	private Menu createMenu() {
		Menu menu = new Menu();
		menu.setXtype("menu");
		return menu;
	}

	private List<MenuItem> createMenuItems() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		MenuItem homepageItem = new MenuItem();
		homepageItem.setText("Homepage");
		homepageItem.setItemId("homepageItem");
		homepageItem.setIconCls("edit-icon");

		MenuItem userProfileItem = new MenuItem();
		userProfileItem.setText("User Profile");
		userProfileItem.setItemId("userProfileItem");
		userProfileItem.setIconCls("edit-icon");

		menuItems.add(homepageItem);
		menuItems.add(userProfileItem);
		return menuItems;
	}

	@Override
	public List<MenuButton> buildMockUserMenu(List<MenuDto> permissibleMenuItems) {
		List<MenuButton> menuButtons = new ArrayList<MenuButton>();
		MenuButton homeButton = createMenuButton("button", "Home", "edit-icon", "left");
		homeButton.setDisplayOrder(1);
		Menu menu = createMenu();
		List<MenuItem> menuItems = createMenuItems();
		menu.setItems(menuItems);
		homeButton.setMenu(menu);
		
		MenuButton matterButton = createMenuButton("button", "Matters", "edit-icon", "left");
		matterButton.setDisplayOrder(2);
		Menu matterMenu = createMenu();
		List<MenuItem> matterMenuItems = createMenuItems();
		matterMenu.setItems(matterMenuItems);
		matterButton.setMenu(matterMenu);
		
		menuButtons.add(homeButton);
		menuButtons.add(matterButton);

		return menuButtons;
	}

}
