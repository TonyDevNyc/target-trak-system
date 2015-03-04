package com.target.trak.system.security.dao;

import java.util.List;

import com.target.trak.system.security.domain.TargetTrakMenu;

public interface MenuDao {

	public List<TargetTrakMenu> selectMenuItemsByPrivileges(final List<Long> privilegeIds);
}
