package com.bjzcyl.service.system.tour.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.tour.TourArticleManager;
import com.bjzcyl.util.PageData;


@Service("tourArticleService")
public class TourArticleService implements TourArticleManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listTourArticle(Page page)throws Exception{
		return (List<PageData>) dao.findForList("TourArticleMapper.tourarticlelistPage", page);
	}
	@Override
	public PageData findByName(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourArticleMapper.findByName", pd);
	}
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourArticleMapper.findById", pd);
	}

	@Override
	public void saveArt(PageData pd) throws Exception {
		dao.save("TourArticleMapper.saveArt", pd);		
	}

	@Override
	public void updateArt(PageData pd) throws Exception {
		dao.update("TourArticleMapper.updateArt", pd);
	}
	
	@Override
	public void deleteArt(PageData pd) throws Exception {
		dao.delete("TourArticleMapper.deleteArt", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<PageData> getTourListForTraveler(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("TourArticleMapper.tourPageListForTraveler", pd);
	}
	@Override
	public PageData getTourListCountForTraveler(PageData pd) throws Exception {
		return (PageData)dao.findForObject("TourArticleMapper.tourListCountForTraveler", pd);
	}

}
