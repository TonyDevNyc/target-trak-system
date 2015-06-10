package com.target.trak.system.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.ConversionService;

import com.target.trak.system.security.dao.MenuDao;
import com.target.trak.system.security.domain.TargetTrakMenu;
import com.target.trak.system.security.service.dto.PrivilegeDto;
import com.target.trak.system.security.service.dto.RoleDto;
import com.target.trak.system.security.service.dto.UserDto;
import com.target.trak.system.security.service.dto.menu.MenuApiRequest;
import com.target.trak.system.security.service.dto.menu.MenuApiResponse;
import com.target.trak.system.security.service.dto.menu.MenuDto;
import com.target.trak.system.service.BaseTargetTrakService;
import com.target.trak.system.service.TargetTrakService;
import com.target.trak.system.service.exception.TargetTrakException;
import com.target.trak.system.validations.TargetTrakValidationError;

public class MenuServiceImpl extends BaseTargetTrakService implements TargetTrakService<MenuApiRequest, MenuApiResponse> {

	private MenuDao menuDao;

	private ConversionService conversionService;

	public MenuServiceImpl(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	@Override
	public MenuApiResponse processRequest(final MenuApiRequest request) throws TargetTrakException {
		MenuApiResponse response = new MenuApiResponse();
		List<MenuDto> menuList = new ArrayList<MenuDto>();
		UserDto currentUser = request.getCurrentUser();

		if (currentUser == null) {
			throw new TargetTrakException("User is null");
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

	@Override
	public List<TargetTrakValidationError> validateRequest(final MenuApiRequest request) throws TargetTrakException {
		return null;
	}

	// @AuditableEvent(auditableEventCode=TargetTrakAuditEventCode.BUILD_USER_MENU)
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