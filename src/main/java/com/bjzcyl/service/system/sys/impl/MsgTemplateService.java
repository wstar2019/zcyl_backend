package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.MsgTemplateManager;
import com.bjzcyl.util.PageData;

@Service("MsgTemplateService")
public class MsgTemplateService implements MsgTemplateManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> msgTemplatelistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysMsgTemplateMapper.msgTemplatelistPage", page);
	}
	@Override
	public void deleteMsgTemplate(PageData pd) throws Exception {
		dao.delete("SysMsgTemplateMapper.deleteMsgTemplate", pd);
	}
	@Override
	public void saveMsgTemplate(PageData pd) throws Exception {
		dao.save("SysMsgTemplateMapper.saveMsgTemplate", pd);		
	}
	@Override
	public PageData getMsgTemplateInfo(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SysMsgTemplateMapper.getMsgTemplateInfo", pd);
	}
	@Override
	public void changeMsgTemplateState(PageData pd) throws Exception {
		dao.update("SysMsgTemplateMapper.changeMsgTemplateState", pd);
	}
}
