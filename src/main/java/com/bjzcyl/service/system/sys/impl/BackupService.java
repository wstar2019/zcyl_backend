package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.BackupManager;
import com.bjzcyl.util.PageData;

@Service("BackupService")
public class BackupService implements BackupManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> backuplistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysBackupMapper.backuplistPage", page);
	}
	@Override
	public void deleteBackup(PageData pd) throws Exception {
		dao.delete("SysBackupMapper.deleteBackup", pd);
	}
	@Override
	public void saveBackup(PageData pd) throws Exception {
		dao.save("SysBackupMapper.saveBackup", pd);		
	}
	public PageData getBackupById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SysBackupMapper.getBackupInfo", pd);
	}
}
