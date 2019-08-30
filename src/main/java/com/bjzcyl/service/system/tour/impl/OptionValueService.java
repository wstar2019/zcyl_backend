package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.tour.OptionValueManager;
import com.bjzcyl.util.PageData;


@Service("optionValueService")
public class OptionValueService implements OptionValueManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listOptVal(Page page)throws Exception{
		return (List<PageData>) dao.findForList("TourOptionValueMapper.optvallistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> listAllOptVal(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("TourOptionValueMapper.allOptVal", pd);
	}

	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourOptionValueMapper.findByName", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> findByMid(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("TourOptionValueMapper.findByMid", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> getValuesOfCbxByMid(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("TourOptionValueMapper.getValuesOfCbxByMid", pd);
	}


	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourOptionValueMapper.findById", pd);
	}

	@Override
	public void saveOptVal(PageData pd) throws Exception {
		dao.save("TourOptionValueMapper.saveOptVal", pd);		
	}

	@Override
	public void editOptVal(PageData pd) throws Exception {
		dao.update("TourOptionValueMapper.editOptVal", pd);
	}
	
	@Override
	public void deleteOptVal(PageData pd) throws Exception {
		dao.update("TourOptionValueMapper.deleteOptVal", pd);
	}

	@Override
	public void deleteAllOptVal(String[] _IDS) throws Exception {
		dao.delete("TourOptionValueMapper.deleteAllOptVal", _IDS);
		
	}

}
