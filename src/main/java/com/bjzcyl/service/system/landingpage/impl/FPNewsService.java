package com.bjzcyl.service.system.landingpage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.landingpage.FPNewsManager;
import com.bjzcyl.util.PageData;

@Service("FPNewsService")
public class FPNewsService implements FPNewsManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listFPNews(Page page) throws Exception {
		return (List<PageData>) dao.findForList("FPNewsMapper.fptourlistPage", page);
	}
//	@SuppressWarnings("unchecked")
//	public List<PageData> fpNewsAll() throws Exception {
//		return (List<PageData>) dao.findForList("FPNewsMapper.fpNewsAll", null);
//	}
//	@SuppressWarnings("unchecked")
//	public List<PageData> getFPNews() throws Exception {
//		return (List<PageData>) dao.findForList("FPNewsMapper.getFPNews", null);
//	}
//	@Override
//	public void saveFPNews(PageData pd) throws Exception {
//		dao.save("FPNewsMapper.saveFPNews", pd);
//	}
//	@Override
//	public void updateFPNews(PageData pd) throws Exception {
//		dao.save("FPNewsMapper.updateFPNews", pd);
//	}
//	@Override
//	public void updateFPNewsViewNum(PageData pd) throws Exception {
//		dao.save("FPNewsMapper.updateFPNewsViewNum", pd);
//	}
//	@Override
//	public void updateFPNewsViewState(PageData pd) throws Exception {
//		dao.save("FPNewsMapper.updateFPNewsViewState", pd);
//	}
//	@Override
//	public void deleteFPNews(PageData pd) throws Exception {
//		dao.delete("FPNewsMapper.deleteFPNews", pd);
//	}	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PageData> hasFPNews(PageData pd) throws Exception {
//		return (List<PageData>)dao.findForList("FPNewsMapper.hasFPNews", pd);
//	}

}
