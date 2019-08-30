package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SAOptionValueManager;
import com.bjzcyl.util.PageData;


@Service("saOptionValueService")
public class SAOptionValueService implements SAOptionValueManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listOptVal(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SAOptionValueMapper.optvallistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> listAllOptVal(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("SAOptionValueMapper.allOptVal", pd);
	}

	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAOptionValueMapper.findByName", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> findByMid(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SAOptionValueMapper.findByMid", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> getValuesOfCbxByMid(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SAOptionValueMapper.getValuesOfCbxByMid", pd);
	}


	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAOptionValueMapper.findById", pd);
	}

	@Override
	public void saveOptVal(PageData pd) throws Exception {
		dao.save("SAOptionValueMapper.saveOptVal", pd);		
	}

	@Override
	public void editOptVal(PageData pd) throws Exception {
		dao.update("SAOptionValueMapper.editOptVal", pd);
	}
	
	@Override
	public void deleteOptVal(PageData pd) throws Exception {
		dao.delete("SAOptionValueMapper.deleteOptVal", pd);
	}

	@Override
	public void deleteAllOptVal(String[] _IDS) throws Exception {
		dao.delete("SAOptionValueMapper.deleteAllOptVal", _IDS);
		
	}
	@Override
	public void delOptValWithMID(PageData pd) throws Exception{
		dao.delete("SAOptionValueMapper.delOptValWithMID", pd);
	}

}
