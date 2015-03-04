package com.target.trak.system.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.target.trak.system.security.dao.MenuDao;
import com.target.trak.system.security.domain.TargetTrakMenu;
import com.target.trak.system.security.dto.RoleDto;
import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.security.dto.menu.MenuDto;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private ConversionService conversionService;
	
	@Override
	public List<MenuDto> getMenuItemsForUser(final UserDto user) throws TargetTrakSecurityException {
		List<MenuDto> menuList = new ArrayList<MenuDto>();
		if (user == null) {
			throw new TargetTrakSecurityException("User is null");
		}
		
		List<RoleDto> roles = user.getRoles();
		if (roles == null || roles.isEmpty()) {
			return menuList;
		}
		
		List<Long> privilegeIds = buildPrivilegeList(roles);
		List<TargetTrakMenu> menuItems = menuDao.selectMenuItemsByPrivileges(privilegeIds);
		menuList = buildMenuList(menuItems);
		return menuList;
	}
	
	private List<MenuDto> buildMenuList(List<TargetTrakMenu> menuItems) {
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		for (TargetTrakMenu menuItem : menuItems) {
			dtos.add(conversionService.convert(menuItem, MenuDto.class));
		}
		return dtos;
	}
	
	private List<Long> buildPrivilegeList(List<RoleDto> roles) {
		List<Long> privilegeIds = new ArrayList<Long>();
		for (RoleDto role : roles) {
			privilegeIds.add(role.getRoleId());
		}
		return privilegeIds;
	}

}
