package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.ManagerManager;
import com.bjzcyl.util.PageData;

@Service("ManagerService")
public class ManagerService implements ManagerManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	public PageData findManager(PageData pd)throws Exception
	{
		return (PageData)dao.findForObject("SysManagerMapper.findManager", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> managerlistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysManagerMapper.managerlistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> listAllManager(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SysManagerMapper.listAllManager", pd);	
	}
	@Override
	public void updatePass(PageData pd) throws Exception {
		dao.update("SysManagerMapper.updatePass", pd);
	}
	@Override
	public void updateManager(PageData pd) throws Exception {
		dao.update("SysManagerMapper.updateManager", pd);
	}
	@Override
	public void updateManagerRole(PageData pd)throws Exception
	{
		dao.update("SysManagerMapper.updateManagerRole", pd);
	}
	@Override
	public void updateManagerState(PageData pd)throws Exception
	{
		dao.update("SysManagerMapper.updateManagerState", pd);
	}
	@Override
	public void saveManager(PageData pd) throws Exception {
		dao.save("SysManagerMapper.saveManager", pd);		
	}
	@Override
	public void deleteManager(PageData pd) throws Exception {
		dao.delete("SysManagerMapper.deleteManager", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> findByName(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SysManagerMapper.findByName", pd);
	}
	public PageData findByLGID(PageData pd) throws Exception
	{
		return (PageData) dao.findForObject("SysManagerMapper.findByLGID", pd);
	}
	public PageData getManagerByNameAndPwd(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SysManagerMapper.getManagerInfo", pd);
	}
	public void initManagerRole(PageData pd) throws Exception{
		dao.update("SysManagerMapper.initManagerRole", pd);
	}
}
