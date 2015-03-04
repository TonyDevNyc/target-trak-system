package com.target.trak.system.security.service;

import java.util.List;

import com.target.trak.system.security.dto.UserDto;
import com.target.trak.system.security.dto.menu.MenuDto;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;

public interface MenuService {

	public List<MenuDto> getMenuItemsForUser(final UserDto user) throws TargetTrakSecurityException;
}
