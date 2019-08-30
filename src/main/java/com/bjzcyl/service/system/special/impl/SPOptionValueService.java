package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPOptionValueManager;
import com.bjzcyl.util.PageData;


@Service("spOptionValueService")
public class SPOptionValueService implements SPOptionValueManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listOptVal(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SPOptionValueMapper.optvallistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> listAllOptVal(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("SPOptionValueMapper.allOptVal", pd);
	}

	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPOptionValueMapper.findByName", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> findByMid(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SPOptionValueMapper.findByMid", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> getValuesOfCbxByMid(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SPOptionValueMapper.getValuesOfCbxByMid", pd);
	}


	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPOptionValueMapper.findById", pd);
	}

	@Override
	public void saveOptVal(PageData pd) throws Exception {
		dao.save("SPOptionValueMapper.saveOptVal", pd);		
	}

	@Override
	public void editOptVal(PageData pd) throws Exception {
		dao.update("SPOptionValueMapper.editOptVal", pd);
	}
	
	@Override
	public void deleteOptVal(PageData pd) throws Exception {
		dao.delete("SPOptionValueMapper.deleteOptVal", pd);
	}

	@Override
	public void deleteAllOptVal(String[] _IDS) throws Exception {
		dao.delete("SPOptionValueMapper.deleteAllOptVal", _IDS);
		
	}
	@Override
	public void delOptValWithMID(PageData pd) throws Exception{
		dao.delete("SPOptionValueMapper.delOptValWithMID", pd);
	}

}
