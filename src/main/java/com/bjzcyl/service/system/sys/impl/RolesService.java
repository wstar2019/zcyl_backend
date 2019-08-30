package com.bjzcyl.service.system.sys.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.RolesManager;
import com.bjzcyl.util.PageData;

@Service("RolesService")
public class RolesService implements RolesManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> rolelistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysRoleMapper.rolelistPage", page);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> roleList(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("SysRoleMapper.roleList", pd);
	}
	@Override
	public void saveRole(PageData pd) throws Exception {
		dao.save("SysRoleMapper.saveRole", pd);		
	}
	@Override
	public void deleteRole(PageData pd) throws Exception {
		dao.delete("SysRoleMapper.deleteRole", pd);
	}
	@Override
	public void updateRole(PageData pd) throws Exception {
		dao.update("SysRoleMapper.updateRole", pd);
	}
	@Override
	public PageData getRoleByRoleID(PageData pd)throws Exception
	{
		return (PageData)dao.findForObject("SysRoleMapper.getRolesById", pd);
	}
}
