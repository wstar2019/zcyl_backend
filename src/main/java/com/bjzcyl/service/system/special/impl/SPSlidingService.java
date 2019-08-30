package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPSlidingManager;
import com.bjzcyl.util.PageData;

@Service("SPSlidingService")
public class SPSlidingService implements SPSlidingManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listSPSlide(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SPSlidingMapper.spslidinglistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> spSlideAll() throws Exception {
		return (List<PageData>) dao.findForList("SPSlidingMapper.spSlideAll", null);
	}
	
	@Override
	public void saveSPSlide(PageData pd) throws Exception {
		dao.save("SPSlidingMapper.saveSPSlide", pd);
	}

	@Override
	public void updateSPSlide(PageData pd) throws Exception {
		dao.save("SPSlidingMapper.updateSPSlide", pd);
	}
	
	@Override
	public void deleteSPSlide(PageData pd) throws Exception {
		dao.delete("SPSlidingMapper.deleteSPSlide", pd);
	}
	
	@Override
	public void changeStateSlide(PageData pd) throws Exception {
		dao.update("SPSlidingMapper.changeStateSlide", pd);
	}

}
