package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.service.system.special.SPBookingLogManager;
import com.bjzcyl.util.PageData;


@Service("spBookingLogService")
public class SPBookingLogService implements SPBookingLogManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> findByBookingId(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("SPBookingLogMapper.findByBookingId", pd);
	}

}
