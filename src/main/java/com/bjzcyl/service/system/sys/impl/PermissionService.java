package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.system.SysPermission;
import com.bjzcyl.service.system.sys.PermissionManager;



@Service("permissionService")
public class PermissionService implements PermissionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SysPermission> listPermissionByMenuId(String menuId) throws Exception {
		return (List<SysPermission>) dao.findForList("SysPermissionMapper.listPermissionByMenuId", menuId);
	}
}
