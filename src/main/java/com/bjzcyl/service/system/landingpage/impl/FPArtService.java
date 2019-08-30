package com.bjzcyl.service.system.landingpage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.landingpage.FPArtManager;
import com.bjzcyl.util.PageData;

@Service("FPArtService")
public class FPArtService implements FPArtManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listFPArt(Page page) throws Exception {
		return (List<PageData>) dao.findForList("FPArtMapper.fpartlistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> fpArtAll() throws Exception {
		return (List<PageData>) dao.findForList("FPArtMapper.fpArtAll", null);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> getFPArt() throws Exception {
		return (List<PageData>) dao.findForList("FPArtMapper.getFPArt", null);
	}
	@Override
	public void saveFPArt(PageData pd) throws Exception {
		dao.save("FPArtMapper.saveFPArt", pd);
	}
	@Override
	public void updateFPArt(PageData pd) throws Exception {
		dao.save("FPArtMapper.updateFPArt", pd);
	}
	@Override
	public void updateFPArtViewNum(PageData pd) throws Exception {
		dao.save("FPArtMapper.updateFPArtViewNum", pd);
	}
	@Override
	public void updateFPArtViewState(PageData pd) throws Exception {
		dao.save("FPArtMapper.updateFPArtViewState", pd);
	}
	@Override
	public void deleteFPArt(PageData pd) throws Exception {
		dao.delete("FPArtMapper.deleteFPArt", pd);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> hasFPArt(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("FPArtMapper.hasFPArt", pd);
	}

}
