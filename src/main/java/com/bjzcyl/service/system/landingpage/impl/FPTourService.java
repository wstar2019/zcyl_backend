package com.bjzcyl.service.system.landingpage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.landingpage.FPTourManager;
import com.bjzcyl.util.PageData;

@Service("FPTourService")
public class FPTourService implements FPTourManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listFPTour(Page page) throws Exception {
		return (List<PageData>) dao.findForList("FPTourMapper.fptourlistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> fpTourAll() throws Exception {
		return (List<PageData>) dao.findForList("FPTourMapper.fpTourAll", null);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> getFPTour() throws Exception {
		return (List<PageData>) dao.findForList("FPTourMapper.getFPTour", null);
	}
	@Override
	public void saveFPTour(PageData pd) throws Exception {
		dao.save("FPTourMapper.saveFPTour", pd);
	}
	@Override
	public void updateFPTour(PageData pd) throws Exception {
		dao.save("FPTourMapper.updateFPTour", pd);
	}
	@Override
	public void updateFPTourViewNum(PageData pd) throws Exception {
		dao.save("FPTourMapper.updateFPTourViewNum", pd);
	}
	@Override
	public void updateFPTourViewState(PageData pd) throws Exception {
		dao.save("FPTourMapper.updateFPTourViewState", pd);
	}
	@Override
	public void deleteFPTour(PageData pd) throws Exception {
		dao.delete("FPTourMapper.deleteFPTour", pd);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> hasFPTour(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("FPTourMapper.hasFPTour", pd);
	}

}
