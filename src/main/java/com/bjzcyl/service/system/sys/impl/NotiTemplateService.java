package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.NotiTemplateManager;
import com.bjzcyl.util.PageData;

@Service("NotiTemplateService")
public class NotiTemplateService implements NotiTemplateManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> notiTemplatelistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysNotiTemplateMapper.notiTemplatelistPage", page);
	}
	@Override
	public void deleteNotiTemplate(PageData pd) throws Exception {
		dao.delete("SysNotiTemplateMapper.deleteNotiTemplate", pd);
	}
	@Override
	public void saveNotiTemplate(PageData pd) throws Exception {
		dao.save("SysNotiTemplateMapper.saveNotiTemplate", pd);		
	}
	@Override
	public PageData getNotiTemplateInfo(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SysNotiTemplateMapper.getNotiTemplateInfo", pd);
	}
	@Override
	public void sendNotify(PageData pd) throws Exception {
		dao.update("SysNotiTemplateMapper.sendNotify", pd);
	}
}
