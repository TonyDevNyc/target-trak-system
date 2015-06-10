package com.target.trak.system.security.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.target.trak.system.security.domain.TargetTrakMenu;
import com.target.trak.system.security.service.dto.menu.MenuDto;

public class MenuDtoConverter implements Converter<TargetTrakMenu, MenuDto> {

	@Override
	public MenuDto convert(final TargetTrakMenu domain) {
		MenuDto dto = new MenuDto();
		dto.setMenuId(domain.getMenuId());
		dto.setParentMenuId(domain.getParentMenuId());
		dto.setItemId(domain.getItemId());
		dto.setDisplayOrder(domain.getDisplayOrder());
		dto.setText(domain.getText());
		dto.setIconCss(domain.getIconCss());
		dto.setPrivilegeNeeded(domain.getPrivilegeNeeded());
		return dto;
	}
}
