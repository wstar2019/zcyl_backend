package com.bjzcyl.service.system.landingpage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.landingpage.FPSlidingManager;
import com.bjzcyl.util.PageData;

@Service("FPSlidingService")
public class FPSlidingService implements FPSlidingManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listFPSlide(Page page) throws Exception {
		return (List<PageData>) dao.findForList("FPSlidingMapper.fpslidinglistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> fpSlideAll() throws Exception {
		return (List<PageData>) dao.findForList("FPSlidingMapper.fpSlideAll", null);
	}
	
	@Override
	public void saveFPSlide(PageData pd) throws Exception {
		dao.save("FPSlidingMapper.saveFPSlide", pd);
	}

	@Override
	public void updateFPSlide(PageData pd) throws Exception {
		dao.save("FPSlidingMapper.updateFPSlide", pd);
	}
	
	@Override
	public void deleteFPSlide(PageData pd) throws Exception {
		dao.delete("FPSlidingMapper.deleteFPSlide", pd);
	}

}
