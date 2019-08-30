package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SAOptionManager;
import com.bjzcyl.util.PageData;


@Service("saOptionService")
public class SAOptionService implements SAOptionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listOption(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SAOptionMapper.optionlistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> listAllOption(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("SAOptionMapper.listAllOption", pd);
	}


	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAOptionMapper.findByName", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAOptionMapper.findById", pd);
	}
	
	@Override
	public void saveOpt(PageData pd) throws Exception {
		dao.save("SAOptionMapper.saveOpt", pd);		
	}

	@Override
	public void editOpt(PageData pd) throws Exception {
		dao.update("SAOptionMapper.editOpt", pd);
	}
	
	@Override
	public void changeStateOpt(PageData pd) throws Exception {
		dao.update("SAOptionMapper.changeStateOpt", pd);
	}

	@Override
	public void deleteOpt(PageData pd) throws Exception {
		dao.delete("SAOptionMapper.deleteOpt", pd);
	}

	@Override
	public void deleteAllOpt(String[] _IDS) throws Exception {
		dao.delete("SAOptionMapper.deleteAllOpt", _IDS);
		
	}

}
