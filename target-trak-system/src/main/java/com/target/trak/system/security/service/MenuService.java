package com.target.trak.system.security.service;

import com.target.trak.system.security.dto.menu.MenuApiRequest;
import com.target.trak.system.security.dto.menu.MenuApiResponse;
import com.target.trak.system.security.exceptions.TargetTrakSecurityException;

public interface MenuService {

	public MenuApiResponse getMenuItemsForUser(final MenuApiRequest request) throws TargetTrakSecurityException;
}
