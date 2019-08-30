package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SASlidingManager;
import com.bjzcyl.util.PageData;

@Service("SASlidingService")
public class SASlidingService implements SASlidingManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listSASlide(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SASlidingMapper.saslidinglistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> saSlideAll() throws Exception {
		return (List<PageData>) dao.findForList("SASlidingMapper.saSlideAll", null);
	}
	
	@Override
	public void saveSASlide(PageData pd) throws Exception {
		dao.save("SASlidingMapper.saveSASlide", pd);
	}

	@Override
	public void updateSASlide(PageData pd) throws Exception {
		dao.save("SASlidingMapper.updateSASlide", pd);
	}
	
	@Override
	public void deleteSASlide(PageData pd) throws Exception {
		dao.delete("SASlidingMapper.deleteSASlide", pd);
	}
	
	@Override
	public void changeStateSlide(PageData pd) throws Exception {
		dao.update("SASlidingMapper.changeStateSlide", pd);
	}

}
