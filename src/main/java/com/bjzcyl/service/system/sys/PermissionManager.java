package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.system.SysPermission;

public interface PermissionManager {
		
	public List<SysPermission> listPermissionByMenuId(String parentId)throws Exception;
}
