package com.target.trak.system.web.views.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.target.trak.system.security.dto.menu.MenuDto;
import com.target.trak.system.web.views.ui.menu.ExtJsChildMenu;
import com.target.trak.system.web.views.ui.menu.ExtJsMenuItem;
import com.target.trak.system.web.views.ui.menu.ExtJsParentMenu;

@Component("menuBuilder")
public class MenuBuilderImpl implements MenuBuilder {

	private static final String MENU_XTYPE = "menu";
	private static final String MENU_ITEM_XTYPE = "menuitem";
	private static final String ICON_POSITION = "left";

	@Override
	public List<ExtJsParentMenu> buildUserInterfaceMenu(final List<MenuDto> menuItems) {
		List<ExtJsParentMenu> menuList = buildParentChildList(menuItems);
		for (ExtJsParentMenu menuItem : menuList) {
			System.out.println(menuItem.getText() + " Display Order: " + menuItem.getDisplayOrder());
		}
		return menuList;
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

	private List<ExtJsParentMenu> buildParentChildList(final List<MenuDto> menuItems) {
		List<MenuDto> parentList = getParentMenuItems(menuItems);
		List<ExtJsParentMenu> menuList = new ArrayList<ExtJsParentMenu>();

		ExtJsParentMenu menu = null;
		Long parentId = null;

		for (MenuDto parent : parentList) {
			parentId = parent.getMenuId();
			menu = buildExtJsParentMenu(parent, getChildList(parentId, menuItems));
			menuList.add(menu);
		}

		return menuList;
	}

	private ExtJsParentMenu buildExtJsParentMenu(final MenuDto parentDto, final List<MenuDto> childItems) {
		ExtJsParentMenu parent = new ExtJsParentMenu();
		parent.setXtype(MENU_ITEM_XTYPE);
		parent.setText(parentDto.getText());
		parent.setIconCls(parentDto.getIconCss());
		parent.setIconAlign(ICON_POSITION);
		parent.setDisplayOrder(parentDto.getDisplayOrder());

		parent.setMenu(buildSubMenuItems(childItems));

		return parent;
	}

	private ExtJsChildMenu buildSubMenuItems(final List<MenuDto> childItems) {
		ExtJsChildMenu menu = new ExtJsChildMenu();
		menu.setXtype(MENU_XTYPE);
		List<ExtJsMenuItem> menuItems = new ArrayList<ExtJsMenuItem>();

		ExtJsMenuItem menuItem = null;
		for (MenuDto childItem : childItems) {
			menuItem = buildSubMenuItem(childItem);
			menuItems.add(menuItem);
		}
		menu.setItems(menuItems);
		return menu;
	}

	private ExtJsMenuItem buildSubMenuItem(MenuDto childItem) {
		ExtJsMenuItem menuItem = new ExtJsMenuItem();
		menuItem.setText(childItem.getText());
		menuItem.setItemId(childItem.getItemId());
		menuItem.setIconCls(childItem.getIconCss());
		return menuItem;
	}

	private List<MenuDto> getChildList(final Long parentMenuId, final List<MenuDto> childMenuItems) {
		List<MenuDto> childList = new ArrayList<MenuDto>();

		for (MenuDto childMenuItem : childMenuItems) {
			if (parentMenuId.longValue() == childMenuItem.getParentMenuId().longValue()) {
				childList.add(childMenuItem);
			}
		}
		return childList;
	}

}
