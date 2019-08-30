package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.tour.TourOptionManager;
import com.bjzcyl.util.PageData;


@Service("tourOptionService")
public class TourOptionService implements TourOptionManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listTourOption(Page page)throws Exception{
		return (List<PageData>) dao.findForList("TourOptionMapper.touroptionlistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> listAllTourOption(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("TourOptionMapper.listAllTourOption", pd);
	}


	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourOptionMapper.findByName", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourOptionMapper.findById", pd);
	}
	@Override
	public PageData findByMid(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourOptionMapper.findByMid", pd);
	}

	@Override
	public void saveOpt(PageData pd) throws Exception {
		dao.save("TourOptionMapper.saveOpt", pd);		
	}

	@Override
	public void editOpt(PageData pd) throws Exception {
		dao.update("TourOptionMapper.editOpt", pd);
	}
	
	@Override
	public void changeStateOpt(PageData pd) throws Exception {
		dao.update("TourOptionMapper.changeStateOpt", pd);
	}

	@Override
	public void deleteOpt(PageData pd) throws Exception {
		dao.delete("TourOptionMapper.deleteOpt", pd);
	}

	@Override
	public void deleteAllOpt(String[] _IDS) throws Exception {
		dao.delete("TourOptionMapper.deleteAllOpt", _IDS);
		
	}

}
