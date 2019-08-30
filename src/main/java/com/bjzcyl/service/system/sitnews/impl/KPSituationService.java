package com.bjzcyl.service.system.sitnews.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sitnews.KPSituationManager;
import com.bjzcyl.util.PageData;

@Service("KPSituationService")
public class KPSituationService implements KPSituationManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> kpSituationlistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("KPSituationMapper.kpSituationlistPage", page);
	}
	@Override
	public void deleteSN(PageData pd) throws Exception {
		dao.delete("KPSituationMapper.deleteSN", pd);
	}
	@Override
	public void saveSN(PageData pd) throws Exception {
		dao.save("KPSituationMapper.saveSN", pd);		
	}

	@Override
	public void updateSN(PageData pd) throws Exception {
		dao.update("KPSituationMapper.updateSN", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("KPSituationMapper.findById", pd);
	}
	@Override
	public PageData prevSituation(PageData pd) throws Exception {
		return (PageData)dao.findForObject("KPSituationMapper.prevSituation", pd);
	}
	@Override
	public PageData nextSituation(PageData pd) throws Exception {
		return (PageData)dao.findForObject("KPSituationMapper.nextSituation", pd);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> getSitsListForTraveler(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("KPSituationMapper.kpSitslistForTraveler", pd);
	}
}
