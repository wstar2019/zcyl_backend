package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.service.system.art.SABookingLogManager;
import com.bjzcyl.util.PageData;


@Service("saBookingLogService")
public class SABookingLogService implements SABookingLogManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> findByBookingId(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SABookingLogMapper.findByBookingId", pd);
	}
	@Override
	public void saveBookingLog(PageData pd) throws Exception {
		dao.save("SABookingLogMapper.saveBookingLog", pd);		
	}
}
