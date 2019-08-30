package com.bjzcyl.service.system.sitnews.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sitnews.KPNewsManager;
import com.bjzcyl.util.PageData;

@Service("KPNewsService")
public class KPNewsService implements KPNewsManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> kpNewslistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("KPNewsMapper.kpNewslistPage", page);
	}
	@Override
	public void deleteSN(PageData pd) throws Exception {
		dao.delete("KPNewsMapper.deleteSN", pd);
	}
	@Override
	public void saveSN(PageData pd) throws Exception {
		dao.save("KPNewsMapper.saveSN", pd);		
	}
	@Override
	public PageData prevNews(PageData pd) throws Exception {
		return (PageData)dao.findForObject("KPSituationMapper.prevNews", pd);
	}
	@Override
	public PageData nextNews(PageData pd) throws Exception {
		return (PageData)dao.findForObject("KPSituationMapper.nextNews", pd);
	}
	@Override
	public void updateSN(PageData pd) throws Exception {
		dao.update("KPNewsMapper.updateSN", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("KPNewsMapper.findById", pd);
	}
	
	@Override
	public void updateTravelerFPViewState(PageData pd) throws Exception {
		dao.save("KPNewsMapper.updateTravelerFPViewState", pd);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> getFPNews() throws Exception {
		return (List<PageData>) dao.findForList("KPNewsMapper.getFPNews", null);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> getFPNewsList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("KPNewsMapper.getFPNewsList", pd);
	}
	public PageData getFPListCount(PageData pd) throws Exception {
		return (PageData)dao.findForObject("KPNewsMapper.fpNewsCount", pd);
	}
}
