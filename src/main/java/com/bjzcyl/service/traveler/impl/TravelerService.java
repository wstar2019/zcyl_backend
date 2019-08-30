package com.bjzcyl.service.traveler.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.service.traveler.TravelerManager;
import com.bjzcyl.util.PageData;

@Service("TravelerService")
public class TravelerService implements TravelerManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	public void updatePassword(PageData pd) throws Exception {
		dao.update("TravelerMapper.updatePassword", pd);
	}

	@Override
	public void updateTraveler(PageData pd) throws Exception {
		dao.update("TravelerMapper.updateTraveler", pd);
	}

	@Override
	public void registTraveler(PageData pd) throws Exception {
		dao.save("TravelerMapper.registTraveler", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> findByLGID(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("TravelerMapper.findByLGID", pd);
	}

	@Override
	public PageData loginTraveler(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TravelerMapper.loginTraveler", pd);
	}

}
