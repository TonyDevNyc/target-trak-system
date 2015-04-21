package com.target.trak.system.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.ConversionService;

import com.target.trak.system.security.dao.MenuDao;
import com.target.trak.system.security.domain.TargetTrakMenu;
import com.target.trak.system.security.dto.PrivilegeDto;
import com.target.trak.system.security.dto.RoleDto;
import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.security.dto.menu.MenuApiRequest;
import com.target.trak.system.security.dto.menu.MenuApiResponse;
import com.target.trak.system.security.dto.menu.MenuDto;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;
import com.target.trak.system.security.service.MenuService;

public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao;

	private ConversionService conversionService;

	public MenuServiceImpl(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	// @AuditableEvent(auditableEventCode=TargetTrakAuditEventCode.BUILD_USER_MENU)
	@Override
	public MenuApiResponse getMenuItemsForUser(final MenuApiRequest request) throws TargetTrakSecurityException {
		MenuApiResponse response = new MenuApiResponse();
		List<MenuDto> menuList = new ArrayList<MenuDto>();
		UserDto currentUser = request.getCurrentUser();

		if (currentUser == null) {
			throw new TargetTrakSecurityException("User is null");
		}

		List<RoleDto> roles = currentUser.getRoles();
		if (roles == null || roles.isEmpty()) {
			response.setSuccess(false);
			response.setMessage("User [" + currentUser.getUsername() + "] does not have any roles");
			response.setMenuItems(menuList);
			return response;
		}

		List<Long> privilegeIds = buildPrivilegeList(roles);
		List<TargetTrakMenu> menuItems = menuDao.selectMenuItemsByPrivileges(privilegeIds);
		menuList = buildMenuList(menuItems);
		response.setSuccess(true);
		response.setMenuItems(menuList);
		return response;
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
		Map<Long, Long> map = new HashMap<Long, Long>();
		for (RoleDto role : roles) {
			for (PrivilegeDto privilege : role.getPrivileges()) {
				map.put(privilege.getPrivilegeId(), privilege.getPrivilegeId());
			}
		}
		privilegeIds.addAll(map.values());
		return privilegeIds;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
}