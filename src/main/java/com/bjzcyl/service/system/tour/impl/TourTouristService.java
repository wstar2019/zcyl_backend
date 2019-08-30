package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.tour.TourTouristManager;
import com.bjzcyl.util.PageData;


@Service("tourTouristService")
public class TourTouristService implements TourTouristManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listTourTourist(Page page)throws Exception{
		return (List<PageData>) dao.findForList("TourTouristMapper.tourtouristlistPage", page);
	}

	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourTouristMapper.findByName", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourTouristMapper.findById", pd);
	}

	@Override
	public void saveTourist(PageData pd) throws Exception {
		dao.save("TourTouristMapper.saveTourist", pd);		
	}

	@Override
	public void editTourist(PageData pd) throws Exception {
		dao.update("TourTouristMapper.editTourist", pd);
	}
	
	@Override
	public void deleteTourist(PageData pd) throws Exception {
		dao.delete("TourTouristMapper.deleteTourist", pd);
	}

}
