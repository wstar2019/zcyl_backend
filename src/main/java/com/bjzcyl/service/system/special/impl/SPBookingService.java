package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPBookingManager;
import com.bjzcyl.util.PageData;


@Service("spBookingService")
public class SPBookingService implements SPBookingManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listBooking(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SPBookingMapper.bookinglistPage", page);
	}

	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("SPBookingMapper.findById", pd);
	}

}
