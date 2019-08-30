package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.tour.TourSlidingManager;
import com.bjzcyl.util.PageData;

@Service("TourSlidingService")
public class TourSlidingService implements TourSlidingManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listTourSlide(Page page) throws Exception {
		return (List<PageData>) dao.findForList("TourSlidingMapper.tourslidinglistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> tourSlideAll() throws Exception {
		return (List<PageData>) dao.findForList("TourSlidingMapper.tourSlideAll", null);
	}
	
	@Override
	public void saveTourSlide(PageData pd) throws Exception {
		dao.save("TourSlidingMapper.saveTourSlide", pd);
	}

	@Override
	public void updateTourSlide(PageData pd) throws Exception {
		dao.save("TourSlidingMapper.updateTourSlide", pd);
	}
	
	@Override
	public void deleteTourSlide(PageData pd) throws Exception {
		dao.delete("TourSlidingMapper.deleteTourSlide", pd);
	}
	
	@Override
	public void changeStateSlide(PageData pd) throws Exception {
		dao.update("TourSlidingMapper.changeStateSlide", pd);
	}

}
