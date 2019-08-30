package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.service.system.tour.TourBookingLogManager;
import com.bjzcyl.util.PageData;


@Service("tourBookingLogService")
public class TourBookingLogService implements TourBookingLogManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> findByBookingId(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("TourBookingLogMapper.findByBookingId", pd);
	}

	@Override
	public void insertBookingLog(PageData pd) throws Exception {
		dao.save("TourBookingLogMapper.insertBookingLog", pd);
	}

}
