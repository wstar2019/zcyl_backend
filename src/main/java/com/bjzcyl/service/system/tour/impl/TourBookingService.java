package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.tour.TourBookingManager;
import com.bjzcyl.util.PageData;


@Service("tourBookingService")
public class TourBookingService implements TourBookingManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listTourBooking(Page page) throws Exception {
		return (List<PageData>) dao.findForList("TourBookingMapper.tourbookinglistPage", page);
	}

	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("TourBookingMapper.findById", pd);
	}

	@Override
	public void insertBooking(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.save("TourBookingMapper.insertBooking", pd);
	}
	@Override
	public void updateBookingState(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("TourBookingMapper.updateBookingState", pd);
	}

}
