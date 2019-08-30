package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPOptionManager;
import com.bjzcyl.util.PageData;


@Service("spOptionService")
public class SPOptionService implements SPOptionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listOption(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SPOptionMapper.optionlistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> listAllOption(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("SPOptionMapper.listAllOption", pd);
	}


	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPOptionMapper.findByName", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPOptionMapper.findById", pd);
	}
	
	@Override
	public void saveOpt(PageData pd) throws Exception {
		dao.save("SPOptionMapper.saveOpt", pd);		
	}

	@Override
	public void editOpt(PageData pd) throws Exception {
		dao.update("SPOptionMapper.editOpt", pd);
	}
	
	@Override
	public void changeStateOpt(PageData pd) throws Exception {
		dao.update("SPOptionMapper.changeStateOpt", pd);
	}

	@Override
	public void deleteOpt(PageData pd) throws Exception {
		dao.delete("SPOptionMapper.deleteOpt", pd);
	}

	@Override
	public void deleteAllOpt(String[] _IDS) throws Exception {
		dao.delete("SPOptionMapper.deleteAllOpt", _IDS);
		
	}

}
