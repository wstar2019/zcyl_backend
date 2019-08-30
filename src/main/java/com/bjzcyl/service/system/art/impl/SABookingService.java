package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SABookingManager;
import com.bjzcyl.util.PageData;


@Service("saBookingService")
public class SABookingService implements SABookingManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listBooking(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SABookingMapper.bookinglistPage", page);
	}

	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SABookingMapper.findById", pd);
	}
	@Override
	public void updateBookingState(PageData pd) throws Exception {
		dao.update("SABookingMapper.updateBookingState", pd);
	}	
}
